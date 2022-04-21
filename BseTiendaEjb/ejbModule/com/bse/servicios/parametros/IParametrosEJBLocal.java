package com.bse.servicios.parametros;


import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface IParametrosEJBLocal {

	public String actualizarParametro(DTSesion dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEException, Exception;

	public String agregarParametro(DTSesion dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEException, Exception;

	public String agregarRol(DTSesion dtSesion, String rol) throws BSEException, Exception;

	public String agregarOperacion(DTSesion dtSesion, String interfaz, String metodo, short activo) throws Exception, BSEException;

	public String agregarRolOperacion(DTSesion dtSesion, String rol, String interfaz, String metodo) throws Exception, BSEException;

	public String agregarUsuarioRol(DTSesion dtSesion, String rol, String usuario) throws Exception, BSEException;

	public String clearCache(DTSesion dtSesion) throws Exception, BSEException;

}
