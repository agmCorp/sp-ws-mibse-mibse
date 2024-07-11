package com.bse.servicios.parametros;


import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IParametrosTiendaEJBLocal {

	public String actualizarParametro(DTSesionTienda dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEExceptionTienda, Exception;

	public String agregarParametro(DTSesionTienda dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEExceptionTienda, Exception;

	public String agregarRol(DTSesionTienda dtSesion, String rol) throws BSEExceptionTienda, Exception;

	public String agregarOperacion(DTSesionTienda dtSesion, String interfaz, String metodo, short activo) throws Exception, BSEExceptionTienda;

	public String agregarRolOperacion(DTSesionTienda dtSesion, String rol, String interfaz, String metodo) throws Exception, BSEExceptionTienda;

	public String agregarUsuarioRol(DTSesionTienda dtSesion, String rol, String usuario) throws Exception, BSEExceptionTienda;

	public String clearCache(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda;

}
