package uy.com.bse.cotizadorvya.ws;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizadorvya.operaciones.ParamCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ParamMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ParamObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ParamPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ParamRentaMaxima;
import uy.com.bse.cotizadorvya.operaciones.ResultCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ResultMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ResultObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ResultPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ResultRentaMaxima;
import uy.com.bse.serviciosEJB.CotizadorVYALocal;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

@WebService(endpointInterface = "uy.com.bse.cotizadorvya.ws.IWsServiciosCotizadorVYA", serviceName = "WsServiciosCotizadorVYA")
public class WsServiciosCotizadorVYA implements IWsServiciosCotizadorVYA {
	private static Logger LOG = LogManager.getLogger(WsServiciosCotizadorVYA.class);

	public ResultCalcularPlanes calcularPlanes(ParamCalcularPlanes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosCotizadorVYA.class);
		logueo.setMetodo("calcularPlanes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
        logueo.setParametro("codMoneda", param.getCodMoneda());
		logueo.setParametro("fechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("capital", param.getCapital());
		logueo.setParametro("codCategoria", param.getCodCategoria());
		logueo.setParametro("codPlan", param.getCodPlan());
		logueo.setParametro("muerteAccidental", param.getMuerteAccidental());
		logueo.setParametro("invalidez", param.getInvalidez());
		logueo.setParametro("ingresoSeguro", param.getIngresoSeguro());
		logueo.setParametro("ingresoMensual", param.getIngresoMensual());
		logueo.setParametro("rentaIngresoSeguro", param.getRentaIngresoSeguro());
		
		ResultCalcularPlanes datos = new ResultCalcularPlanes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVYA validar = new ValidacionesCotizadorVYA();
			ServiciosError error = validar.validarCalcularPlanes(param);
			
			if (error == null) {
				datos = WsServiciosCotizadorVYA.getEJBManager().calcularPlanes(param);
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
	
	public static CotizadorVYALocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		
		
		return (CotizadorVYALocal) ctx.lookup("global/ServiciosRector/IRectorEJB/CotizadorVYA!uy.com.bse.serviciosEJB.CotizadorVYALocal");
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

	public ResultObtenerCoberturas obtenerCoberturas(ParamObtenerCoberturas param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosCotizadorVYA.class);
		logueo.setMetodo("obtenerCoberturas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		ResultObtenerCoberturas datos = new ResultObtenerCoberturas();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVYA validar = new ValidacionesCotizadorVYA();
			ServiciosError error = validar.validarObtenerCoberturas(param);
			
			if (error == null) {
				datos = WsServiciosCotizadorVYA.getEJBManager().obtenerCoberturas(param);
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

	public ResultMontosIniciales montosIniciales(ParamMontosIniciales param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosCotizadorVYA.class);
		logueo.setMetodo("montosIniciales");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		ResultMontosIniciales datos = new ResultMontosIniciales();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVYA validar = new ValidacionesCotizadorVYA();
			ServiciosError error = validar.validarMontosIniciales(param);
			
			if (error == null) {
				datos = WsServiciosCotizadorVYA.getEJBManager().montosIniciales(param);
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

	public ResultRentaMaxima obtenerRentaMaxima(ParamRentaMaxima param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosCotizadorVYA.class);
		logueo.setMetodo("obtenerRentaMaxima");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		ResultRentaMaxima datos = new ResultRentaMaxima();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVYA validar = new ValidacionesCotizadorVYA();
			ServiciosError error = validar.validarObtenerRentaMaxima(param);
			
			if (error == null) {
				datos = WsServiciosCotizadorVYA.getEJBManager().obtenerRentaMaxima(param);
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

	public ResultPorcentajeAnticipo obtenerPorcentajeAnticipo(ParamPorcentajeAnticipo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosCotizadorVYA.class);
		logueo.setMetodo("obtenerPorcentajeAnticipo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		ResultPorcentajeAnticipo datos = new ResultPorcentajeAnticipo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVYA validar = new ValidacionesCotizadorVYA();
			ServiciosError error = validar.validarObtenerPorcentajeAnticipo(param);
			
			if (error == null) {
				datos = WsServiciosCotizadorVYA.getEJBManager().obtenerPorcentajeAnticipo(param);
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
}
