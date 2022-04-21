package com.bse.negocio.seguridad;

import com.bse.accesodatos.comun.PatUsuarioIp;
import com.bse.accesodatos.comun.PatUsuarioIpPK;
import com.bse.accesodatos.seguridad.PatOperacion;
import com.bse.accesodatos.seguridad.PatOperacionPK;
import com.bse.accesodatos.seguridad.PatRol;
import com.bse.accesodatos.seguridad.PatSesion;
import com.bse.accesodatos.seguridad.PatUsuario;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;
import com.bse.negocio.comun.BSEException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;

public class SeguridadMgr implements ISeguridad {

    private SeguridadMgr() {
    }

    public static ISeguridad getSeguridadMgr() {
        return new SeguridadMgr();
    }

   // @Override
    public short validarCredenciales(EntityManager em, DTSesion sesion, String interfaz, String metodo) {
//    	System.out.println("*****************Usuario " + sesion.getNombreUsuario());
//    	System.out.println("*****************interfaz " + interfaz);
//    	System.out.println("*****************metodo " + metodo);
    	
        if (sesion.getNombreUsuario() != null) {
        	PatUsuario us = em.find(PatUsuario.class, sesion.getNombreUsuario());
       	
            short cred = auditarCredenciales(em, sesion, us, interfaz, metodo);
            if ((cred == Codigos.login_ok) || (cred == CodigosError.debe_cambiar_contrasena)) {
                us.setContador((short) 0);
            } else {
                if (us != null) {
                    if ((us.getContador() + 1) == Codigos.getCodigos().getTopeLogin(em)) {
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
            return CodigosError.login_error_usuario_contrasena;
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
    
    private boolean validarIP(EntityManager em, DTSesion sesion, PatUsuario us) {
        if (us.getValidarIp() == 1) {
            PatUsuarioIp ent = em.find(PatUsuarioIp.class, new PatUsuarioIpPK(sesion.getNombreUsuario(), sesion.getDireccionIp()));
            return ent != null;
        } else {
            return true;
        }
    }

    private short auditarCredenciales(EntityManager em, DTSesion sesion, PatUsuario us, String interfaz, String metodo) {
        String contrasena = sesion.getContrasena();
        if (us != null) {
            if ((us.getFechaFin() == null) || (us.getFechaFin().compareTo(new Date()) >= 0)) {
                if (validarIP(em, sesion, us)) {
                    String contrasenaMD5;
                    try {
                        contrasenaMD5 = encriptarContrasena(contrasena);                       
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(SeguridadMgr.class.getName()).log(Level.ERROR, "login_error_otros", ex);
                        return CodigosError.excepcion_generica;
                    }
                    
                    //System.out.println("BASE->" + us.getContrasena().trim());
                    //System.out.println("MD5 ->" + contrasenaMD5.trim());
                    
                    if (us.getContrasena().trim().equalsIgnoreCase(contrasenaMD5)) {
                        for (PatRol r : us.getPatRolList()) {
                            for (PatOperacion o : r.getPatOperacionList()) {
                                if (o.getPatOperacionPK().getInterfaz().trim().equalsIgnoreCase(interfaz)
                                        && o.getPatOperacionPK().getMetodo().trim().equalsIgnoreCase(metodo)
                                        && (o.getActivo() == Codigos.codigo_true)) {
                                    if (us.getCambiarContrasena() == Codigos.codigo_true) {
                                        return CodigosError.debe_cambiar_contrasena;
                                    } else {
                                        return Codigos.login_ok;
                                    }
                                }
                            }
                        }
                        Logger.getLogger(SeguridadMgr.class.getName()).log(Level.INFO, "login_error_credenciales");
                        return CodigosError.login_error_credenciales;
                    } else {
                    	
                    	//System.out.println("*****************PASS IGUALES? " + us.getContrasena().trim().equalsIgnoreCase(contrasenaMD5));
                    	
                        Logger.getLogger(SeguridadMgr.class.getName()).log(Level.INFO, "login_error_usuario_contrasena");
                        return CodigosError.login_error_usuario_contrasena;
                    }
                } else {
                    Logger.getLogger(SeguridadMgr.class.getName()).log(Level.INFO, "login_error_ip_invalida");
                    return CodigosError.login_error_ip_invalida;
                }
            } else {
                Logger.getLogger(SeguridadMgr.class.getName()).log(Level.INFO, "login_error_usuario_bloqueado");
                return CodigosError.login_error_usuario_bloqueado;
            }
        } else {
        	//System.out.println("*****************US NULO? " + us);

            Logger.getLogger(SeguridadMgr.class.getName()).log(Level.INFO, "login_error_usuario_contrasena");
            return CodigosError.login_error_usuario_contrasena;
        }
    }

    //@Override
    public long crearSesion(EntityManager em, DTSesion sesion, String interfaz, String metodo, short resultado, String parametros) {
        String usuario = sesion.getNombreUsuario();
        if (usuario == null) {
            usuario = "null";
        }
        String direccionIp = sesion.getDireccionIp();
        PatSesion s = new PatSesion();
        s.setNombreUsuario(usuario);
        s.setDireccionIp(direccionIp);
        PatOperacionPK opk = new PatOperacionPK();
        opk.setInterfaz(interfaz);
        opk.setMetodo(metodo);
        PatOperacion operacion = em.find(PatOperacion.class, opk);
        s.setPatOperacion(operacion);
        Date fecha = Calendar.getInstance().getTime();
        Timestamp fechaLogueo = new Timestamp(fecha.getTime());
        s.setFechaLogueo(fechaLogueo);
        s.setResultado(resultado);
        s.setParametros(parametros.substring(0, Math.min(parametros.length(), 250)));
        em.persist(s);
        return s.getIdSesion();
    }

    public void cambiarContrasena(EntityManager em, DTSesion sesion, String nuevaContrasena) throws BSEException, NoSuchAlgorithmException {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(Codigos.patron_de_contrasena);
        matcher = pattern.matcher(nuevaContrasena);
        if (matcher.matches()) {
            PatUsuario us = em.find(PatUsuario.class, sesion.getNombreUsuario());
            us.setContrasena(encriptarContrasena(nuevaContrasena));
            us.setCambiarContrasena(Codigos.codigo_false);
            em.merge(us);
        } else {
            throw new BSEException(CodigosError.no_cumple_patron);
        }
    }
}