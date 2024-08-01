package uy.com.bse.guc.ws;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.GUCRemote;
import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.guc.interfaces.ResultCambioClave;
import uy.com.bse.guc.interfaces.ResultLogin;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

@WebService(endpointInterface = "uy.com.bse.guc.ws.IWsServiciosGUC", serviceName = "WsServiciosGUC")
public class WsServiciosGUC implements IWsServiciosGUC {

	private static Logger LOG = LogManager.getLogger(WsServiciosGUC.class);

	public ResultLogin login(ParamLogin param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosGUC.class);
		logueo.setMetodo("login");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		
		ResultLogin datos = new ResultLogin();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesGUC validar = new ValidacionesGUC();
			ServiciosError error = validar.validarLogin(param);
			if (error == null) {
				datos = WsServiciosGUC.getEJBManager().login(param);
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

	public ResultValidar validar(ParamValidar param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosGUC.class);
		logueo.setMetodo("validar");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultValidar datos = new ResultValidar();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesGUC validar = new ValidacionesGUC();
			ServiciosError error = validar.validarValidar(param);
			if (error == null) {
				datos = WsServiciosGUC.getEJBManager().validar(param);
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

	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosGUC.class);
		logueo.setMetodo("altaUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultAltaUsuario datos = new ResultAltaUsuario();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesGUC validar = new ValidacionesGUC();
			ServiciosError error = validar.validarAltaUsuario(param);
			if (error == null) {
				datos = WsServiciosGUC.getEJBManager().altaUsuario(param);
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

	public ResultCambioClave cambioClave(ParamCambioClave param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosGUC.class);
		logueo.setMetodo("cambioClave");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		ResultCambioClave datos = new ResultCambioClave();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesGUC validar = new ValidacionesGUC();
			ServiciosError error = validar.validarCambioClave(param);
			if (error == null) {
				datos = WsServiciosGUC.getEJBManager().cambioClave(param);
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

	public ResultOlvidoClave olvidoClave(ParamOlvidoClave param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosGUC.class);
		logueo.setMetodo("olvidoClave");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultOlvidoClave datos = new ResultOlvidoClave();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesGUC validar = new ValidacionesGUC();
			ServiciosError error = validar.validarOlvidoClave(param);
			if (error == null) {
				datos = WsServiciosGUC.getEJBManager().olvidoClave(param);
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

	public static GUCRemote getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (GUCRemote) ctx.lookup("global/GUC/GUCEJB/GUC!uy.com.bse.guc.GUCRemote");
	}

	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}

}
