package com.bse.negocio.parametros;

import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.comun.PatParametros;
import com.bse.accesodatos.comun.PatParametrosPK;
import com.bse.accesodatos.seguridad.PatOperacion;
import com.bse.accesodatos.seguridad.PatOperacionPK;
import com.bse.accesodatos.seguridad.PatRol;
import com.bse.accesodatos.seguridad.PatUsuario;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.Codigos;


public class ParametrosMgr implements IParametros{

	private ParametrosMgr() {
    }

	public static IParametros getEViajerosMgr() {
        return new ParametrosMgr();
    }

	public static IParametros getParametrosMgr() {
        return new ParametrosMgr();
	}

	@Override
	public String actualizarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEException {
		PatParametrosPK pk = new PatParametrosPK();
		pk.setAplicacion(aplicacion);
		pk.setClave(clave);
		pk.setSubclave(subclave);
		PatParametros par = em.find(PatParametros.class, pk);
		
		System.out.println("-> parametro encontrado : " + par);
		
		if (par == null){
			return "PAR_NOT_FOUND";
		}
		
		par.setValor(valor);
		em.merge(par);

		return "OK";
	}

	@Override
	public String agregarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEException {
		PatParametrosPK pk = new PatParametrosPK();
		pk.setAplicacion(aplicacion);
		pk.setClave(clave);
		pk.setSubclave(subclave);
		PatParametros par = new PatParametros();
		par.setPatParametrosPK(pk);
		par.setValor(valor);
		em.persist(par);

		return "OK";
	}

	@Override
	public String agregarRol(EntityManager em, String rol) throws Exception, BSEException {
		PatRol patRol = new PatRol();
		patRol.setNombreRol(rol);
		em.persist(patRol);

		return "OK";
	}

	@Override
	public String agregarOperacion(EntityManager em, String interfaz, String metodo, short activo) throws Exception, BSEException {
		PatOperacion patOperacion = new PatOperacion();
		PatOperacionPK patOperacionPK = new PatOperacionPK();
		patOperacionPK.setInterfaz(interfaz);
		patOperacionPK.setMetodo(metodo);
		patOperacion.setActivo(activo);
		patOperacion.setPatOperacionPK(patOperacionPK);
		em.persist(patOperacion);
		
		return "OK";
	}

	@Override
	public String agregarRolOperacion(EntityManager em, String rol, String interfaz, String metodo) throws Exception, BSEException {
		PatRol patRol = em.find(PatRol.class, rol);

//		System.out.println("ROL : " + patRol.getNombreRol());
		
		PatOperacionPK patOperacionPk = new PatOperacionPK();
		patOperacionPk.setInterfaz(interfaz);
		patOperacionPk.setMetodo(metodo);
		PatOperacion patOperacion = em.find(PatOperacion.class, patOperacionPk);
		
		List<PatRol> roles = patOperacion.getPatRolList();
		roles.add(patRol);
		patOperacion.setPatRolList(roles);
		
//		System.out.println("ROL OP LIST : " + patRol.getPatOperacionList());
//		System.out.println("ROL OP LIST SIZE : " + patRol.getPatOperacionList().size());

		List<PatOperacion> listaOperaciones = patRol.getPatOperacionList();
		listaOperaciones.add(patOperacion);
		patRol.setPatOperacionList(listaOperaciones);
		
		em.merge(patRol);
		em.merge(patOperacion);
		em.flush();
		
		return "OK";
	}

	@Override
	public String agregarUsuarioRol(EntityManager em, String rol, String usuario) throws Exception, BSEException {
		PatRol patRol = em.find(PatRol.class, rol);
		PatUsuario patUsuario = em.find(PatUsuario.class, usuario);
		
		List<PatUsuario> usuariosList = patRol.getPatUsuarioList();
		usuariosList.add(patUsuario);
		patRol.setPatUsuarioList(usuariosList);
		
		return "OK";
	}

	@Override
	public String clearCache(EntityManager em) throws Exception, BSEException {
		Codigos.getCodigos().clearCache(em);
		return "OK";
	}


    
}
