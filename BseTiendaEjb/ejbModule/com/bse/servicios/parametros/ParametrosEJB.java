package com.bse.servicios.parametros;


import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.parametros.IParametros;
import com.bse.servicios.seguridad.Seguridadbseonlinews;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "ParametrosEJB")
@Interceptors({Seguridadbseonlinews.class})
public class ParametrosEJB implements IParametrosEJBLocal {

    @PersistenceContext(unitName = "bseonlinews-pu")
    private EntityManager em;

	@Override
	public String actualizarParametro(DTSesion dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEException, Exception {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.actualizarParametro(em, aplicacion, clave, subclave, valor);
	}

	@Override
	public String agregarParametro(DTSesion dtSesion, String aplicacion, String clave, String subclave, String valor) throws BSEException, Exception {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarParametro(em, aplicacion, clave, subclave, valor);
	}

	@Override
	public String agregarRol(DTSesion dtSesion, String rol) throws BSEException, Exception {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarRol(em, rol);
	}

	@Override
	public String agregarOperacion(DTSesion dtSesion, String interfaz, String metodo, short activo) throws Exception, BSEException {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarOperacion(em, interfaz, metodo, activo);
	}

	@Override
	public String agregarRolOperacion(DTSesion dtSesion, String rol, String interfaz, String metodo) throws Exception, BSEException {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarRolOperacion(em, rol, interfaz, metodo);
	}

	@Override
	public String agregarUsuarioRol(DTSesion dtSesion, String rol, String usuario) throws Exception, BSEException {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.agregarUsuarioRol(em, rol, usuario);
	}

	@Override
	public String clearCache(DTSesion dtSesion) throws Exception, BSEException {
		IParametros parametrosManager = FabricaNegocio.getFabricaNegocio().getParametrosMgr();
		return parametrosManager.clearCache(em);
	}

}
