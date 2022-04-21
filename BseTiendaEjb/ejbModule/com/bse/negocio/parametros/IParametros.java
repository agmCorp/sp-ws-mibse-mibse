package com.bse.negocio.parametros;

import javax.persistence.EntityManager;

import com.bse.negocio.comun.BSEException;

public interface IParametros {

	public String actualizarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEException;

	public String agregarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEException;

	public String agregarRol(EntityManager em, String rol) throws Exception, BSEException;

	public String agregarOperacion(EntityManager em, String interfaz, String metodo, short activo) throws Exception, BSEException;

	public String agregarRolOperacion(EntityManager em, String rol, String interfaz, String metodo) throws Exception, BSEException;

	public String agregarUsuarioRol(EntityManager em, String rol, String usuario) throws Exception, BSEException;

	public String clearCache(EntityManager em) throws Exception, BSEException;
	
}
