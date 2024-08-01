package uy.com.bse.seguridad.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ParamLoginGroup;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ParamValidar;
import uy.com.bse.utilitario.seguridad.ResultLogin;
import uy.com.bse.utilitario.seguridad.ResultLoginGroup;
import uy.com.bse.utilitario.seguridad.ResultLogout;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.util.MailBuilder;


@WebService(endpointInterface = "uy.com.bse.seguridad.ws.IWsSeguridad", serviceName = "WsSeguridadServicios")
public class WsSeguridadServicios implements IWsSeguridad{
	
	
	private static final Logger LOG = LogManager.getLogger(WsSeguridadServicios.class);
		
	public ResultLogin login (ParamLogin param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsSeguridadServicios.class);
		logueo.setMetodo("login");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		ResultLogin datos = new ResultLogin();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesSeguridad validar = new ValidacionesSeguridad();
			ServiciosError error = validar.validarParametrosLogin(param);

			if (error == null) {
				datos = WsSeguridadServicios.getEJBManager().login(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	public ResultLoginGroup loginGroup (ParamLoginGroup param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsSeguridadServicios.class);
		logueo.setMetodo("loginGroup");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		ResultLoginGroup datos = new ResultLoginGroup();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesSeguridad validar = new ValidacionesSeguridad();
			ServiciosError error = validar.validarLoginGroup(param);

			if (error == null) {
				datos = WsSeguridadServicios.getEJBManager().loginGroup(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	
	
	public ResultValidar validar (ParamValidar param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsSeguridadServicios.class);
		logueo.setMetodo("validar");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		ResultValidar datos = new ResultValidar();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesSeguridad validar = new ValidacionesSeguridad();
			ServiciosError error = validar.validarValidar(param);

			if (error == null) {
				datos = WsSeguridadServicios.getEJBManager().validar(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
		
	}

	public ResultLogout logout (ParamLogout param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsSeguridadServicios.class);
		logueo.setMetodo("logout");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		ResultLogout datos = new ResultLogout();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesSeguridad validar = new ValidacionesSeguridad();
			ServiciosError error = validar.validarLogout(param);

			if (error == null) {
				datos = WsSeguridadServicios.getEJBManager().logout(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	public static SeguridadServiciosRemote getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		
		return (SeguridadServiciosRemote) ctx.lookup("java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");
		
	}



	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		enviarMail( logueo);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}
	
	
	// FIXME OIGRES... ESTO POR PRUEBAS... NO PONER EN PRODUCCION
		private void enviarMail(Logueo logueo) {

			try {
				EARPropertiesManager manager= new EARPropertiesManager("configSeguridad.properties");
				MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("seguridadServicios.mail.soporte"),manager.obtenerValor("seguridadServicios.ccmail.soporte"),logueo.getMensaje(), "SERVICIO SEGURIDAD Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
			} catch (UnknownHostException e) {
				LOG.error("Error en m\u00e9todo enviarMail", e);
			}
		}


}
