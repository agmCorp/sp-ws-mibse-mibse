package uy.com.bse.autodata.ws;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.autodata.operaciones.ResultConsultaAutodata;
import uy.com.bse.autodata.operaciones.ResultObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ResultObtenerModelosAutodata;
import uy.com.bse.serviciosEJB.AutodataLocal;
import uy.com.bse.usuarios.ws.WsServiciosUsuarios;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

@WebService(endpointInterface = "uy.com.bse.autodata.ws.IWsServiciosAutodata", serviceName = "WsServiciosAutodata")
public class WsServiciosAutodata implements IWsServiciosAutodata {
	private static Logger LOG = LogManager.getLogger(WsServiciosUsuarios.class);

	public ResultConsultaAutodata consultaAutodata(ParamConsultaAutodata param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosAutodata.class);
		logueo.setMetodo("consultaAutodata");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("CodMarca", param.getCodMarca());
		logueo.setParametro("CodAno", param.getCodAno());
		logueo.setParametro("CodModelo", param.getCodModelo());

		ResultConsultaAutodata datos = new ResultConsultaAutodata();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesAutodata validar = new ValidacionesAutodata();
			ServiciosError error = validar.validarConsultaAutodata(param);

			if (error == null) {
				datos = WsServiciosAutodata.getEJBManager().consultaAutodata(param);
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
	
	public ResultObtenerDatosAuto obtenerDatosAuto(ParamObtenerDatosAuto param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosAutodata.class);
		logueo.setMetodo("obtenerDatosAuto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodAutoData", param.getCodAutoData());
		
		ResultObtenerDatosAuto datos = new ResultObtenerDatosAuto();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesAutodata validar = new ValidacionesAutodata();
			ServiciosError error = validar.validarObtenerDatosAuto(param);

			if (error == null) {
				datos = WsServiciosAutodata.getEJBManager().obtenerDatosAuto(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
		
		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	public ResultObtenerModelosAutodata obtenerModelosAutodata(ParamObtenerModelosAutodata param) {
		
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosAutodata.class);
		logueo.setMetodo("obtenerDatosAuto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		
		ResultObtenerModelosAutodata datos = new ResultObtenerModelosAutodata();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesAutodata validar = new ValidacionesAutodata();
			ServiciosError error = validar.validarObtenerModelosAutodata(param);

			if (error == null) {
				datos = WsServiciosAutodata.getEJBManager().obtenerModelosAutodata(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
		
		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	public static AutodataLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (AutodataLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Autodata!uy.com.bse.serviciosEJB.AutodataLocal");
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
