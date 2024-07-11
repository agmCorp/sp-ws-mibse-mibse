package com.bse.negocio.seguridad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.comun.PatUsuarioIpPKTienda;
import com.bse.accesodatos.comun.PatUsuarioIpTienda;
import com.bse.accesodatos.seguridad.PatOperacionPKTienda;
import com.bse.accesodatos.seguridad.PatOperacionTienda;
import com.bse.accesodatos.seguridad.PatRolTienda;
import com.bse.accesodatos.seguridad.PatSesionTienda;
import com.bse.accesodatos.seguridad.PatUsuarioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

public class SeguridadMgrTienda implements ISeguridadTienda {

    private static final Logger logger = LogManager.getLogger(SeguridadMgrTienda.class);


    private SeguridadMgrTienda() {
    }

    public static ISeguridadTienda getSeguridadMgr() {
        return new SeguridadMgrTienda();
    }

   // @Override
    @Override
    public short validarCredenciales(EntityManager em, DTSesionTienda sesion, String interfaz, String metodo) {
        //logger.info("SeguridadMgrTienda - SEGURIDAD - validarCredenciales - TIENDA");
    	//System.out.println("*****************Usuario " + sesion.getNombreUsuario());
    	//System.out.println("*****************interfaz " + interfaz);
    	//System.out.println("*****************metodo " + metodo);

        if (sesion.getNombreUsuario() != null) {
        	PatUsuarioTienda us = em.find(PatUsuarioTienda.class, sesion.getNombreUsuario());

            short cred = auditarCredenciales(em, sesion, us, interfaz, metodo);
            if ((cred == CodigosTienda.login_ok) || (cred == CodigosErrorTienda.debe_cambiar_contrasena)) {
                us.setContador((short) 0);
            } else {
                if (us != null) {
                    //System.out.println("Contador   : " + us.getContador());
                    //System.out.println("Tope Login : " + CodigosTienda.getCodigos().getTopeLogin(em));
                    if ((us.getContador() + 1) == CodigosTienda.getCodigos().getTopeLogin(em)) {
                        us.setFechaFin(new Date());
                    } else {
                    	if (us.getNombreUsuario().equalsIgnoreCase("abitab") ||
                    			us.getNombreUsuario().equalsIgnoreCase("redpagos") ||
                    			us.getNombreUsuario().equalsIgnoreCase("correos")||
                    			us.getNombreUsuario().equalsIgnoreCase("sistarbanc")){
                            us.setContador((short)0);
                    	}
                        us.setContador((short) (us.getContador() + 1));
                    }
                }
            }
            if (us != null) {
                em.merge(us);
            }
            return cred;
        } else {
            return CodigosErrorTienda.login_error_usuario_contrasena;
        }
    }

    private String encriptarContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest algorithm;
        byte[] defaultBytes = contrasena.getBytes();
        algorithm = MessageDigest.getInstance("MD5");

        algorithm.reset();
        algorithm.update(defaultBytes);
        byte messageDigest[] = algorithm.digest();

        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xff & messageDigest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private boolean validarIP(EntityManager em, DTSesionTienda sesion, PatUsuarioTienda us) {
        if (us.getValidarIp() == 1) {
            PatUsuarioIpTienda ent = em.find(PatUsuarioIpTienda.class, new PatUsuarioIpPKTienda(sesion.getNombreUsuario(), sesion.getDireccionIp()));
            return ent != null;
        } else {
            return true;
        }
    }

    private short auditarCredenciales(EntityManager em, DTSesionTienda sesion, PatUsuarioTienda us, String interfaz, String metodo) {
        String contrasena = sesion.getContrasena();
        if (us != null) {
            //System.out.println("us.getFechaFin() :" + us.getFechaFin().toString());
            //System.out.println("new date" + new Date().toString());
            //System.out.println("compare :" +(us.getFechaFin().compareTo(new Date())));
            if ((us.getFechaFin() == null) || (us.getFechaFin().compareTo(new Date()) >= 0)) {
                if (validarIP(em, sesion, us)) {
                    String contrasenaMD5;
                    try {
                        contrasenaMD5 = encriptarContrasena(contrasena);
                    } catch (NoSuchAlgorithmException ex) {
                        //Logger.getLogger(SeguridadMgrTienda.class.getName()).log(Level.ERROR, "login_error_otros", ex);
                        logger.log(Level.ERROR, "login_error_otros", ex);
                        return CodigosErrorTienda.excepcion_generica;
                    }

                    //System.out.println("BASE->" + us.getContrasena().trim());
                    //System.out.println("MD5 ->" + contrasenaMD5.trim());

                    if (us.getContrasena().trim().equalsIgnoreCase(contrasenaMD5)) {
                        //System.out.println("ENTRO CONTRASE�A IGUAL");
                        //System.out.println("Interfaz buscada: " + interfaz);
                        //System.out.println("Metodo   buscado: " + metodo);
                        for (PatRolTienda r : us.getPatRolList()) {
                            //System.out.println("Nombre rol: " + r.getNombreRol());
                            for (PatOperacionTienda o : r.getPatOperacionList()) {
                                //System.out.println("Interfaz Loop: " + o.getPatOperacionPK().getInterfaz());
                                //System.out.println("Metodo Loop: " + o.getPatOperacionPK().getMetodo());
                                if (o.getPatOperacionPK().getInterfaz().trim().equalsIgnoreCase(interfaz)
                                        && o.getPatOperacionPK().getMetodo().trim().equalsIgnoreCase(metodo)
                                        && (o.getActivo() == CodigosTienda.codigo_true)) {
                                    if (us.getCambiarContrasena() == CodigosTienda.codigo_true) {
                                        return CodigosErrorTienda.debe_cambiar_contrasena;
                                    } else {
                                        return CodigosTienda.login_ok;
                                    }
                                }
                            }
                        }
                        //Logger.getLogger(SeguridadMgrTienda.class.getName()).log(Level.INFO, "login_error_credenciales");
                        logger.log(Level.INFO, "login_error_credenciales");
                        return CodigosErrorTienda.login_error_credenciales;
                    } else {

                    	//System.out.println("*****************PASS IGUALES? " + us.getContrasena().trim().equalsIgnoreCase(contrasenaMD5));

                        //Logger.getLogger(SeguridadMgrTienda.class.getName()).log(Level.INFO, "login_error_usuario_contrasena");
                        logger.log(Level.INFO, "login_error_usuario_contrasena");
                        return CodigosErrorTienda.login_error_usuario_contrasena;
                    }
                } else {
                    //Logger.getLogger(SeguridadMgrTienda.class.getName()).log(Level.INFO, "login_error_ip_invalida");
                    logger.log(Level.INFO, "login_error_ip_invalida");
                    return CodigosErrorTienda.login_error_ip_invalida;
                }
            } else {
                //System.out.println("us.getFechaFin() :" + ((us!=null&&us.getFechaFin()!=null)?us.getFechaFin().toString():null));
                //System.out.println("compare :" +(us.getFechaFin().compareTo(new Date())));
                //Logger.getLogger(SeguridadMgrTienda.class.getName()).log(Level.INFO, "login_error_usuario_bloqueado");
                logger.log(Level.INFO, "login_error_usuario_bloqueado");
                return CodigosErrorTienda.login_error_usuario_bloqueado;
            }
        } else {
        	//System.out.println("*****************US NULO? " + us);

            //Logger.getLogger(SeguridadMgrTienda.class.getName()).log(Level.INFO, "login_error_usuario_contrasena");
            logger.log(Level.INFO, "login_error_usuario_contrasena");
            return CodigosErrorTienda.login_error_usuario_contrasena;
        }
    }

    //@Override
    @Override
    public long crearSesion(EntityManager em, DTSesionTienda sesion, String interfaz, String metodo, short resultado, String parametros) {
        String usuario = sesion.getNombreUsuario();
        if (usuario == null) {
            usuario = "null";
        }
        String direccionIp = sesion.getDireccionIp();
        PatSesionTienda s = new PatSesionTienda();
        s.setNombreUsuario(usuario);
        s.setDireccionIp(direccionIp);
        PatOperacionPKTienda opk = new PatOperacionPKTienda();
        opk.setInterfaz(interfaz);
        opk.setMetodo(metodo);
        PatOperacionTienda operacion = em.find(PatOperacionTienda.class, opk);
        s.setPatOperacion(operacion);
        Date fecha = Calendar.getInstance().getTime();
        Timestamp fechaLogueo = new Timestamp(fecha.getTime());
        s.setFechaLogueo(fechaLogueo);
        s.setResultado(resultado);
        s.setParametros(parametros.substring(0, Math.min(parametros.length(), 250)));
        em.persist(s);
        return s.getIdSesion();
    }

    @Override
    public void cambiarContrasena(EntityManager em, DTSesionTienda sesion, String nuevaContrasena) throws BSEExceptionTienda, NoSuchAlgorithmException {
        System.out.println("SeguridadMgrTienda - SEGURIDAD - cambiarContrasena - TIENDA");
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(CodigosTienda.patron_de_contrasena);
        matcher = pattern.matcher(nuevaContrasena);
        if (matcher.matches()) {
            PatUsuarioTienda us = em.find(PatUsuarioTienda.class, sesion.getNombreUsuario());
            us.setContrasena(encriptarContrasena(nuevaContrasena));
            us.setCambiarContrasena(CodigosTienda.codigo_false);
            em.merge(us);
        } else {
            throw new BSEExceptionTienda(CodigosErrorTienda.no_cumple_patron);
        }
    }
}