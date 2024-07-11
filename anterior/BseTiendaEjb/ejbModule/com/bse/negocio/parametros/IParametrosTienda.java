package com.bse.negocio.parametros;

import javax.persistence.EntityManager;

import com.bse.negocio.comun.BSEExceptionTienda;

public interface IParametrosTienda {

	public String actualizarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEExceptionTienda;

	public String agregarParametro(EntityManager em, String aplicacion, String clave, String subclave, String valor) throws Exception, BSEExceptionTienda;

	public String agregarRol(EntityManager em, String rol) throws Exception, BSEExceptionTienda;

	public String agregarOperacion(EntityManager em, String interfaz, String metodo, short activo) throws Exception, BSEExceptionTienda;

	public String agregarRolOperacion(EntityManager em, String rol, String interfaz, String metodo) throws Exception, BSEExceptionTienda;

	public String agregarUsuarioRol(EntityManager em, String rol, String usuario) throws Exception, BSEExceptionTienda;

	public String clearCache(EntityManager em) throws Exception, BSEExceptionTienda;
	
}
