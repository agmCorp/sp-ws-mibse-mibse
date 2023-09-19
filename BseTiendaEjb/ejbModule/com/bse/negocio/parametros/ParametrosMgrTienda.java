package com.bse.negocio.parametros;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.comun.PatParametrosPKTienda;
import com.bse.accesodatos.comun.PatParametrosTienda;
import com.bse.accesodatos.seguridad.PatOperacionPKTienda;
import com.bse.accesodatos.seguridad.PatOperacionTienda;
import com.bse.accesodatos.seguridad.PatRolTienda;
import com.bse.accesodatos.seguridad.PatUsuarioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosTienda;


public class ParametrosMgrTienda implements IParametrosTienda{

    private static final Logger logger = LogManager.getLogger(ParametrosMgrTienda.class);

    private ParametrosMgrTienda() {
    }

	public static IParametrosTienda getEViajerosMgr() {
        return new ParametrosMgrTienda();
    }

	public static IParametrosTienda getParametrosMgr() {
        return new ParametrosMgrTienda();
	}

	@Override
	public String actualizarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - actualizarParametro - TIENDA - aplicacion["
                        + aplicacion + "] clave[" + clave + "] subclave[" + subclave + "] valor[" + valor + "]");


        PatParametrosPKTienda pk = new PatParametrosPKTienda();
		pk.setAplicacion(aplicacion);
		pk.setClave(clave);
		pk.setSubclave(subclave);
		PatParametrosTienda par = em.find(PatParametrosTienda.class, pk);

		System.out.println("-> parametro encontrado : " + par);

		if (par == null){
			return "PAR_NOT_FOUND";
		}

		par.setValor(valor);
		em.merge(par);

		return "OK";
	}

	@Override
	public String agregarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - agregarParametro - TIENDA - aplicacion["
                            + aplicacion + "] clave[" + clave + "] subclave[" + subclave + "] valor[" + valor + "]");

        PatParametrosPKTienda pk = new PatParametrosPKTienda();
		pk.setAplicacion(aplicacion);
		pk.setClave(clave);
		pk.setSubclave(subclave);
		PatParametrosTienda par = new PatParametrosTienda();
		par.setPatParametrosPK(pk);
		par.setValor(valor);
		em.persist(par);

		return "OK";
	}

	@Override
	public String agregarRol(EntityManager em, String rol) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - agregarRol - TIENDA - rol[" + rol + "]");

		PatRolTienda patRol = new PatRolTienda();
		patRol.setNombreRol(rol);
		em.persist(patRol);

		return "OK";
	}

	@Override
	public String agregarOperacion(EntityManager em, String interfaz, String metodo, short activo) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - agregarOperacion - TIENDA - interfaz["
                                                        + interfaz + "] metodo[" + metodo + "] activo[" + activo + "]");

		PatOperacionTienda patOperacion = new PatOperacionTienda();
		PatOperacionPKTienda patOperacionPK = new PatOperacionPKTienda();
		patOperacionPK.setInterfaz(interfaz);
		patOperacionPK.setMetodo(metodo);
		patOperacion.setActivo(activo);
		patOperacion.setPatOperacionPK(patOperacionPK);
		em.persist(patOperacion);

		return "OK";
	}

	@Override
	public String agregarRolOperacion(EntityManager em, String rol, String interfaz, String metodo) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - agregarRolOperacion - TIENDA - rol["
                                                        + rol + "] interfaz[" + interfaz + "] metodo[" + metodo + "]");

		PatRolTienda patRol = em.find(PatRolTienda.class, rol);

        //		System.out.println("ROL : " + patRol.getNombreRol());

		PatOperacionPKTienda patOperacionPk = new PatOperacionPKTienda();
		patOperacionPk.setInterfaz(interfaz);
		patOperacionPk.setMetodo(metodo);
		PatOperacionTienda patOperacion = em.find(PatOperacionTienda.class, patOperacionPk);

		List<PatRolTienda> roles = patOperacion.getPatRolList();
		roles.add(patRol);
		patOperacion.setPatRolList(roles);

//		System.out.println("ROL OP LIST : " + patRol.getPatOperacionList());
//		System.out.println("ROL OP LIST SIZE : " + patRol.getPatOperacionList().size());

		List<PatOperacionTienda> listaOperaciones = patRol.getPatOperacionList();
		listaOperaciones.add(patOperacion);
		patRol.setPatOperacionList(listaOperaciones);

		em.merge(patRol);
		em.merge(patOperacion);
		em.flush();

		return "OK";
	}

	@Override
	public String agregarUsuarioRol(EntityManager em, String rol, String usuario) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - agregarUsuarioRol - TIENDA - rol["
                                                                                + rol + "] usuario[" + usuario + "]");

		PatRolTienda patRol = em.find(PatRolTienda.class, rol);
		PatUsuarioTienda patUsuario = em.find(PatUsuarioTienda.class, usuario);

		List<PatUsuarioTienda> usuariosList = patRol.getPatUsuarioList();
		usuariosList.add(patUsuario);
		patRol.setPatUsuarioList(usuariosList);

		return "OK";
	}

	@Override
	public String clearCache(EntityManager em) throws Exception, BSEExceptionTienda {
        logger.info("ParametrosMgrTienda - PARAMETROS - clearCache - TIENDA");

		CodigosTienda.getCodigos().clearCache(em);
		return "OK";
	}



}
