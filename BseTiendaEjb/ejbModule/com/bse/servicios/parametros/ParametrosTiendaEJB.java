package com.bse.servicios.parametros;


import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.parametros.IParametrosTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "ParametrosTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class ParametrosTiendaEJB implements IParametrosTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

	@Override
	public String actualizarParametro(DTSesionTienda dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEExceptionTienda, Exception {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.actualizarParametro(em, aplicacion, clave, subclave, valor);
	}

	@Override
	public String agregarParametro(DTSesionTienda dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEExceptionTienda, Exception {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarParametro(em, aplicacion, clave, subclave, valor);
	}

	@Override
	public String agregarRol(DTSesionTienda dtSesion, String rol) throws BSEExceptionTienda, Exception {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarRol(em, rol);
	}

	@Override
	public String agregarOperacion(DTSesionTienda dtSesion, String interfaz, String metodo, short activo) throws Exception, BSEExceptionTienda {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarOperacion(em, interfaz, metodo, activo);
	}

	@Override
	public String agregarRolOperacion(DTSesionTienda dtSesion, String rol, String interfaz, String metodo) throws Exception, BSEExceptionTienda {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarRolOperacion(em, rol, interfaz, metodo);
	}

	@Override
	public String agregarUsuarioRol(DTSesionTienda dtSesion, String rol, String usuario) throws Exception, BSEExceptionTienda {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarUsuarioRol(em, rol, usuario);
	}

	@Override
	public String clearCache(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
		IParametrosTienda parametrosManager = FabricaNegocioTienda.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.clearCache(em);
	}

}
