package uy.com.bse.administracion.ws;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.administracion.ParamAgregarEdoc;
import uy.com.bse.administracion.ParamObtenerDatosCorredor;
import uy.com.bse.administracion.ResultAgregarEdoc;
import uy.com.bse.administracion.ResultObtenerDatosCorredor;
import uy.com.bse.serviciosEJB.AdministracionLocal;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

@WebService(endpointInterface = "uy.com.bse.administracion.ws.IWsServiciosAdministracion", serviceName = "WsServiciosAdministracion")
public class WsServiciosAdministracion implements IWsServiciosAdministracion {
	private static Logger LOG = LogManager.getLogger(WsServiciosAdministracion.class);

	
	/**
	 * 
	 * @param param
	 * @return obtiene los datos de un corredor.
	 */
	public ResultObtenerDatosCorredor obtenerDatosCorredor(ParamObtenerDatosCorredor param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosAdministracion.class);
		logueo.setMetodo("obtenerDatosCorredor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		ResultObtenerDatosCorredor datos = new ResultObtenerDatosCorredor();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesAdministracion validar = new ValidacionesAdministracion();
			ServiciosError error = validar.validarObtenerDatosCorredor(param);

			if (error == null) {
				datos = WsServiciosAdministracion.getEJBManager().obtenerDatosCorredor(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	/**
	 * 
	 * @param param
	 * @return Realiza la adhesion a E-volante de un corredor.
	 */
	
	public ResultAgregarEdoc agregarEvolante(ParamAgregarEdoc param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosAdministracion.class);
		logueo.setMetodo("agregarEvolante");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		

		ResultAgregarEdoc datos = new ResultAgregarEdoc();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesAdministracion validar = new ValidacionesAdministracion();
			ServiciosError error = validar.validarAgregarEvolante(param);

			if (error == null) {
				datos = WsServiciosAdministracion.getEJBManager().agregarEvolante(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public static AdministracionLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (AdministracionLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Administracion!uy.com.bse.serviciosEJB.AdministracionLocal");
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
