package uy.com.bse.cotizaciones.vehiculo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCertificadoCeroVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamValidarDatosVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultValidarDatosVehiculo;
import uy.com.bse.serviciosEJB.CotOperacionesLocal;
import uy.com.bse.serviciosEJB.CotizacionLocal;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.cotizaciones.vehiculo.IWsCotizadorVehiculo", serviceName = "WsCotizadorVehiculo")
public class WsCotizadorVehiculo implements IWsCotizadorVehiculo {

	private static final Logger LOG = LogManager.getLogger(WsCotizadorVehiculo.class);

	// Funcionalidades

	public ResultValidarDatosVehiculo validarDatosVehiculo(ParamValidarDatosVehiculo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorVehiculo.class);
		logueo.setMetodo("validarDatosVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Lista", param.getLista());

		ResultValidarDatosVehiculo datos = new ResultValidarDatosVehiculo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVehiculo validar = new ValidacionesCotizadorVehiculo();
			ServiciosError error = validar.validarValidarDatosVehiculo(param);

			if (error == null) {
				datos = WsCotizadorVehiculo.getEJBOperacionesManager().validarDatosVehiculo(param);
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

	public ResultCalcularCuotasVehiculo calcularCuotasVehiculo(ParamCalcularCuotasVehiculo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorVehiculo.class);
		logueo.setMetodo("calcularCuotasVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultCalcularCuotasVehiculo datos = new ResultCalcularCuotasVehiculo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVehiculo validar = new ValidacionesCotizadorVehiculo();
			ServiciosError error = validar.validarCalcularCuotasVehiculo(param);

			if (error == null) {
				datos = WsCotizadorVehiculo.getEJBOperacionesManager().calcularCuotasVehiculo(param);
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

	public ResultNuevaCotizacionVehiculo nuevaCotizacionVehiculo(ParamNuevaCotizacionVehiculo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorVehiculo.class);
		logueo.setMetodo("nuevaCotizacionVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultNuevaCotizacionVehiculo datos = new ResultNuevaCotizacionVehiculo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVehiculo validar = new ValidacionesCotizadorVehiculo();
			ServiciosError error = validar.validarNuevaCotizacionVehiculo(param);

			if (error == null) {
				datos = WsCotizadorVehiculo.getEJBOperacionesManager().nuevaCotizacionVehiculo(param);
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

	public ResultActualizarPromocionVehiculo actualizarPromocionVehiculo(ParamActualizarPromocionVehiculo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorVehiculo.class);
		logueo.setMetodo("actualizarPromocionVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo de promocion", param.getCodPromocion());

		ResultActualizarPromocionVehiculo datos = new ResultActualizarPromocionVehiculo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVehiculo validar = new ValidacionesCotizadorVehiculo();
			ServiciosError error = validar.validarActualizarPromocionVehiculo(param);

			if (error == null) {
				datos = WsCotizadorVehiculo.getEJBOperacionesManager().actualizarPromocionVehiculo(param);
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

	public ResultListaValoresDato listaValoresDatoParametricoVehiculo(ParamListaValoresDatoVehiculo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorVehiculo.class);
		logueo.setMetodo("listaValoresDatoParametricoVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cantida de filtros", param.getCantFiltros());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Primer filtro", param.getPrimerFiltro());
		logueo.setParametro("Segundo filtro", param.getSegundoFiltro());

		ResultListaValoresDato datos = new ResultListaValoresDato();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVehiculo validar = new ValidacionesCotizadorVehiculo();
			ServiciosError error = validar.validarListaValoresDatoParametricoVehiculo(param);

			if (error == null) {
				datos = WsCotizadorVehiculo.getEJBCotizacionesManager().listaValoresDatoParametricoVehiculo(param);
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

	public ResultGenerico actualizarCertificadoCeroVehiculo(ParamActualizarCertificadoCeroVehiculo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorVehiculo.class);
		logueo.setMetodo("actualizarCertificadoCeroVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Tipo calculo", param.getTipoCalculo());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Forma pago", param.getFormaPago());

		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorVehiculo validar = new ValidacionesCotizadorVehiculo();
			ServiciosError error = validar.validarActualizarCertificadoCeroVehiculo(param);

			if (error == null) {
				datos = WsCotizadorVehiculo.getEJBOperacionesManager().actualizarCertificadoCeroVehiculo(param);
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

	// Utiles

	public static CotOperacionesLocal getEJBOperacionesManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (CotOperacionesLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/CotOperaciones!uy.com.bse.serviciosEJB.CotOperacionesLocal");
	}

	public static CotizacionLocal getEJBCotizacionesManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (CotizacionLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Cotizacion!uy.com.bse.serviciosEJB.CotizacionLocal");
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
	private void enviarMail( Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail( manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO COTIZADORVehiculo Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}

}