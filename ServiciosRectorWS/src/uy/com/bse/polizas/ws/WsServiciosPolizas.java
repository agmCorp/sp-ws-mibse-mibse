package uy.com.bse.polizas.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.polizas.consultas.*;
import uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares;
import uy.com.bse.polizas.operaciones.ParamAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularPoliza;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendienteSinPremio;
import uy.com.bse.polizas.operaciones.ParamEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ParamFacturacionElectronica;
import uy.com.bse.polizas.operaciones.ParamGenerarEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ParamValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ParamValidarPoliza;
import uy.com.bse.polizas.operaciones.ResultAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularPoliza;
import uy.com.bse.polizas.operaciones.ResultCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ResultEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ResultFacturacionElectronica;
import uy.com.bse.polizas.operaciones.ResultNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ResultNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ResultValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ResultValidarPoliza;
import uy.com.bse.recuotificacion.ParamCalcularRec;
import uy.com.bse.recuotificacion.ParamListaCertificadosEndosar;
import uy.com.bse.recuotificacion.ParamListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ParamModificacionSinPremio;
import uy.com.bse.recuotificacion.ParamObtenerConsolidado;
import uy.com.bse.recuotificacion.ParamOrigenEndoso;
import uy.com.bse.recuotificacion.ParamRecuotificarPoliza;
import uy.com.bse.recuotificacion.ParamValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ParamValidarPolizaRec;
import uy.com.bse.recuotificacion.ResultCalcularRec;
import uy.com.bse.recuotificacion.ResultListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ResultModificacionSinPremio;
import uy.com.bse.recuotificacion.ResultObtenerConsolidado;
import uy.com.bse.recuotificacion.ResultOrigenEndoso;
import uy.com.bse.recuotificacion.ResultRecuotificarPoliza;
import uy.com.bse.recuotificacion.ResultValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ResultValidarPolizaRec;
import uy.com.bse.refacturar.ParamRefacturarPoliza;
import uy.com.bse.refacturar.ParamValidarRefacturacion;
import uy.com.bse.refacturar.ResultRefacturacionPoliza;
import uy.com.bse.refacturar.ResultValidarRefacturacion;
import uy.com.bse.serviciosEJB.PolizasLocal;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class WsServiciosPolizas.
 */
@WebService(endpointInterface = "uy.com.bse.polizas.ws.IWsServiciosPolizas", serviceName = "WsServiciosPolizas")
public class WsServiciosPolizas implements IWsServiciosPolizas {

	/** The log. */
	private static Logger LOG = LogManager.getLogger(WsServiciosPolizas.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosPoliza(uy.com.bse
	 * .polizas.consultas.ParamObtenerDatosPoliza)
	 */
	public ResultObtenerDatosPoliza obtenerDatosPoliza(ParamObtenerDatosPoliza param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		ResultObtenerDatosPoliza datos = new ResultObtenerDatosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerCertificadosPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerCertificadosPoliza)
	 */
	public ResultObtenerCertificadosPoliza obtenerCertificadosPoliza(ParamObtenerCertificadosPoliza param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerCertificadosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerCertificadosPoliza datos = new ResultObtenerCertificadosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerCertificadosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerCertificadosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosCertificadoPoliza
	 * (uy.com.bse.polizas.consultas.ParamObtenerDatosCertificadoPoliza)
	 */
	public ResultObtenerDatosCertificadoPoliza obtenerDatosCertificadoPoliza(ParamObtenerDatosCertificadoPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosCertificadoPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerDatosCertificadoPoliza datos = new ResultObtenerDatosCertificadoPoliza();

		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosCertificadoPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosCertificadoPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaTipoAnulacion(uy.com.bse
	 * .polizas.consultas.ParamTipoAnulacion)
	 */
	public ResultTipoAnulacion listaTipoAnulacion(ParamTipoAnulacion param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaTipoAnulacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultTipoAnulacion datos = new ResultTipoAnulacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaTipoAnulacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaTipoAnulacion(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaCausaAnulacion(uy.com.
	 * bse.polizas.consultas.ParamCausaAnulacion)
	 */
	public ResultCausaAnulacion listaCausaAnulacion(ParamCausaAnulacion param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaCausaAnulacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo anulacion", param.getCodTipoAnulacion());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultCausaAnulacion datos = new ResultCausaAnulacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaCausaAnulacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaCausaAnulacion(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaModoCalculoAnulacion(uy
	 * .com.bse.polizas.consultas.ParamModoCalculo)
	 */
	public ResultModoCalculo listaModoCalculoAnulacion(ParamModoCalculo param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaModoCalculoAnulacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultModoCalculo datos = new ResultModoCalculo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaModoCalculoAnulacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaModoCalculoAnulacion(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerEndososPoliza(uy.com
	 * .bse.polizas.consultas.ParamObtenerEndososPoliza)
	 */
	public ResultObtenerEndososPoliza obtenerEndososPoliza(ParamObtenerEndososPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerEndososPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerEndososPoliza datos = new ResultObtenerEndososPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerEndososPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerEndososPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosEndosoPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerDatosEndosoPoliza)
	 */
	public ResultObtenerDatosEndosoPoliza obtenerDatosEndosoPoliza(ParamObtenerDatosEndosoPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosEndosoPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		ResultObtenerDatosEndosoPoliza datos = new ResultObtenerDatosEndosoPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosEndosoPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosEndosoPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerProductoresPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerProductoresPoliza)
	 */
	public ResultObtenerProductoresPoliza obtenerProductoresPoliza(ParamObtenerProductoresPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerProductoresPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerProductoresPoliza datos = new ResultObtenerProductoresPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerProductoresPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerProductoresPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDetallePoliza(uy.com
	 * .bse.polizas.consultas.ParamObtenerDetallePoliza)
	 */
	public ResultObtenerDetallePoliza obtenerDetallePoliza(ParamObtenerDetallePoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDetallePoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerDetallePoliza datos = new ResultObtenerDetallePoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDetallePoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDetallePoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerPolizas(uy.com.bse.polizas
	 * .consultas.ParamObtenerPolizas)
	 */
	public ResultObtenerPolizas obtenerPolizas(ParamObtenerPolizas param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerPolizas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Fecha desde de la poliza", param.getFechaDesde());
		logueo.setParametro("Fecha de emision de la poliza", param.getFechaEmision());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Cliente", param.getCliente());
		logueo.setParametro("Asegurado", param.getAsegurado());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Fecha hasta de la poliza", param.getFechaDesde());
		logueo.setParametro("Vigente", param.getVigentes());
		logueo.setParametro("Orden", param.getOrden());
		logueo.setParametro("Codigo dato parametrico", param.getCodDatoParametrico());
		logueo.setParametro("Valor dato parametrico", param.getValorDatoParametrico());

		ResultObtenerPolizas datos = new ResultObtenerPolizas();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerPolizas(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerPolizas(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerCuotasPoliza(uy.com.
	 * bse.polizas.consultas.ParamObtenerCuotasPoliza)
	 */
	public ResultObtenerCuotasPoliza obtenerCuotasPoliza(ParamObtenerCuotasPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerCuotasPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerCuotasPoliza datos = new ResultObtenerCuotasPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerCuotasPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerCuotasPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerFacturasPoliza(uy.com
	 * .bse.polizas.consultas.ParamObtenerFacturasPoliza)
	 */
	public ResultObtenerFacturasPoliza obtenerFacturasPoliza(ParamObtenerFacturasPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerFacturasPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerFacturasPoliza datos = new ResultObtenerFacturasPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerFacturasPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerFacturasPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerImputacionesPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerImputacionesPoliza)
	 */
	public ResultObtenerImputacionesPoliza obtenerImputacionesPoliza(ParamObtenerImputacionesPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerImputacionesPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerImputacionesPoliza datos = new ResultObtenerImputacionesPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerImputacionesPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerImputacionesPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerRemesasPoliza(uy.com
	 * .bse.polizas.consultas.ParamObtenerRemesasPoliza)
	 */
	public ResultObtenerRemesasPoliza obtenerRemesasPoliza(ParamObtenerRemesasPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerRemesasPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerRemesasPoliza datos = new ResultObtenerRemesasPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerRemesasPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerRemesasPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerTotalesCuotasPoliza(
	 * uy.com.bse.polizas.consultas.ParamObtenerTotalesCuotasPoliza)
	 */
	public ResultObtenerTotalesCuotasPoliza obtenerTotalesCuotasPoliza(ParamObtenerTotalesCuotasPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerTotalesCuotasPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerTotalesCuotasPoliza datos = new ResultObtenerTotalesCuotasPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerTotalesCuotasPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerTotalesCuotasPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerPreliqPendientesPoliza
	 * (uy.com.bse.polizas.consultas.ParamObtenerPreliqPendientesPoliza)
	 */
	public ResultObtenerPreliqPendientesPoliza obtenerPreliqPendientesPoliza(ParamObtenerPreliqPendientesPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerPreliqPendientesPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerPreliqPendientesPoliza datos = new ResultObtenerPreliqPendientesPoliza();

		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerPreliqPendientesPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerPreliqPendientesPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerCotizacionPoliza(uy.
	 * com.bse.polizas.consultas.ParamObtenerCotizacionPoliza)
	 */
	public ResultObtenerCotizacionPoliza obtenerCotizacionPoliza(ParamObtenerCotizacionPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerCotizacionPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerCotizacionPoliza datos = new ResultObtenerCotizacionPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerCotizacionPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerCotizacionPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerContratantePoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerContratantePoliza)
	 */
	public ResultObtenerContratantePoliza obtenerContratantePoliza(ParamObtenerContratantePoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerContratantePoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		ResultObtenerContratantePoliza datos = new ResultObtenerContratantePoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerContratantePoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerContratantePoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerAseguradoCertifPoliza
	 * (uy.com.bse.polizas.consultas.ParamObtenerAseguradoCertifPoliza)
	 */
	public ResultObtenerAseguradoCertifPoliza obtenerAseguradoCertifPoliza(ParamObtenerAseguradoCertifPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerAseguradoCertifPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerAseguradoCertifPoliza datos = new ResultObtenerAseguradoCertifPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerAseguradoCertifPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerAseguradoCertifPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerBienesAseguradosPoliza
	 * (uy.com.bse.polizas.consultas.ParamObtenerBienesAseguradosPoliza)
	 */
	public ResultObtenerBienesAseguradosPoliza obtenerBienesAseguradosPoliza(ParamObtenerBienesAseguradosPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerBienesAseguradosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		ResultObtenerBienesAseguradosPoliza datos = new ResultObtenerBienesAseguradosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerBienesAseguradosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerBienesAseguradosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerTextosPoliza(uy.com.
	 * bse.polizas.consultas.ParamObtenerTextosPoliza)
	 */
	public ResultObtenerTextosPoliza obtenerTextosPoliza(ParamObtenerTextosPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerTextosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		ResultObtenerTextosPoliza datos = new ResultObtenerTextosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerTextosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerTextosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDetalleTextoPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerDetalleTextoPoliza)
	 */
	public ResultObtenerDetalleTextoPoliza obtenerDetalleTextoPoliza(ParamObtenerDetalleTextoPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDetalleTextoPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo texto", param.getNumConsecutivoTexto());
		logueo.setParametro("Codigo texto", param.getCodTexto());

		ResultObtenerDetalleTextoPoliza datos = new ResultObtenerDetalleTextoPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDetalleTextoPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDetalleTextoPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerAnexosPoliza(uy.com.
	 * bse.polizas.consultas.ParamObtenerAnexosPoliza)
	 */
	public ResultObtenerAnexosPoliza obtenerAnexosPoliza(ParamObtenerAnexosPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerAnexosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		ResultObtenerAnexosPoliza datos = new ResultObtenerAnexosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerAnexosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerAnexosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerListaBienesPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerListaBienesPoliza)
	 */
	public ResultObtenerListaBienesPoliza obtenerListaBienesPoliza(ParamObtenerListaBienesPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerListaBienesPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Codigo de bien asegurado", param.getCodBienAsegurado());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		ResultObtenerListaBienesPoliza datos = new ResultObtenerListaBienesPoliza();

		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerListaBienesPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerListaBienesPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerUbicacionBienPoliza(
	 * uy.com.bse.polizas.consultas.ParamObtenerUbicacionBienPoliza)
	 */
	public ResultObtenerUbicacionBienPoliza obtenerUbicacionBienPoliza(ParamObtenerUbicacionBienPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerUbicacionBienPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		ResultObtenerUbicacionBienPoliza datos = new ResultObtenerUbicacionBienPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerUbicacionBienPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerUbicacionBienPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerBeneficiariosPoliza(
	 * uy.com.bse.polizas.consultas.ParamObtenerBeneficiariosPoliza)
	 */
	public ResultObtenerBeneficiariosPoliza obtenerBeneficiariosPoliza(ParamObtenerBeneficiariosPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerBeneficiariosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		ResultObtenerBeneficiariosPoliza datos = new ResultObtenerBeneficiariosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerBeneficiariosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerBeneficiariosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerAcreedoresPoliza(uy.
	 * com.bse.polizas.consultas.ParamObtenerAcreedoresPoliza)
	 */
	public ResultObtenerAcreedoresPoliza obtenerAcreedoresPoliza(ParamObtenerAcreedoresPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerAcreedoresPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Codigo de bien asegurado", param.getCodBienAsegurado());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		ResultObtenerAcreedoresPoliza datos = new ResultObtenerAcreedoresPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerAcreedoresPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerAcreedoresPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerFranquiciasPoliza(uy
	 * .com.bse.polizas.consultas.ParamObtenerFranquiciasPoliza)
	 */
	public ResultObtenerFranquiciasPoliza obtenerFranquiciasPoliza(ParamObtenerFranquiciasPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerFranquiciasPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		ResultObtenerFranquiciasPoliza datos = new ResultObtenerFranquiciasPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerFranquiciasPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerFranquiciasPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerAhorrosPoliza(uy.com
	 * .bse.polizas.consultas.ParamObtenerAhorrosPoliza)
	 */
	public ResultObtenerAhorrosPoliza obtenerAhorrosPoliza(ParamObtenerAhorrosPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerAhorrosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		ResultObtenerAhorrosPoliza datos = new ResultObtenerAhorrosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerAhorrosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerAhorrosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerRemesasXCliente(uy.com
	 * .bse.polizas.consultas.ParamObtenerRemesasXCliente)
	 */
	public ResultObtenerRemesasXCliente obtenerRemesasXCliente(ParamObtenerRemesasXCliente param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerRemesasXCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());

		ResultObtenerRemesasXCliente datos = new ResultObtenerRemesasXCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerRemesasXCliente(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerRemesasXCliente(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosRemesa(uy.com.bse
	 * .polizas.consultas.ParamObtenerDatosRemesa)
	 */
	public ResultObtenerDatosRemesa obtenerDatosRemesa(ParamObtenerDatosRemesa param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosRemesa");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		ResultObtenerDatosRemesa datos = new ResultObtenerDatosRemesa();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosRemesa(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosRemesa(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerCancelacionesRemesa(
	 * uy.com.bse.polizas.consultas.ParamObtenerCancelacionesRemesa)
	 */
	public ResultObtenerCancelacionesRemesa obtenerCancelacionesRemesa(ParamObtenerCancelacionesRemesa param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerCancelacionesRemesa");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		ResultObtenerCancelacionesRemesa datos = new ResultObtenerCancelacionesRemesa();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerCancelacionesRemesa(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerCancelacionesRemesa(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = Values.CLWEBSERVEXCP;
			logueo.setException(Values.EXCEPTION);
			logueo.setError(e.getMessage());
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDetalleRemesa(uy.com
	 * .bse.polizas.consultas.ParamObtenerDetalleRemesa)
	 */
	public ResultObtenerDetalleRemesa obtenerDetalleRemesa(ParamObtenerDetalleRemesa param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDetalleRemesa");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		ResultObtenerDetalleRemesa datos = new ResultObtenerDetalleRemesa();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDetalleRemesa(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDetalleRemesa(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerOrigenRemesaAutomatica
	 * (uy.com.bse.polizas.consultas.ParamObtenerOrigenRemesaAuto)
	 */
	public ResultObtenerOrigenRemesaAuto obtenerOrigenRemesaAutomatica(ParamObtenerOrigenRemesaAuto param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerOrigenRemesaAutomatica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		ResultObtenerOrigenRemesaAuto datos = new ResultObtenerOrigenRemesaAuto();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerOrigenRemesaAutomatica(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerOrigenRemesaAutomatica(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerNotasSiniestro(uy.com
	 * .bse.polizas.consultas.ParamObtenerNotasSiniestro)
	 */
	public ResultObtenerNotasSiniestro obtenerNotasSiniestro(ParamObtenerNotasSiniestro param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerNotasSiniestro");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de enlace", param.getEnlace());

		ResultObtenerNotasSiniestro datos = new ResultObtenerNotasSiniestro();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerNotasSiniestro(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerNotasSiniestro(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerBonifNoSiniestroPoliza
	 * (uy.com.bse.polizas.consultas.ParamObtenerBonifNoSiniestroPoliza)
	 */
	public ResultObtenerBonifNoSiniestroPoliza obtenerBonifNoSiniestroPoliza(ParamObtenerBonifNoSiniestroPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerBonifNoSiniestroPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());

		ResultObtenerBonifNoSiniestroPoliza datos = new ResultObtenerBonifNoSiniestroPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerBonifNoSiniestroPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerBonifNoSiniestroPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDeclaracionSiniestro
	 * (uy.com.bse.polizas.consultas.ParamObtenerDeclaracionSiniestro)
	 */
	public ResultObtenerDeclaracionSiniestro obtenerDeclaracionSiniestro(ParamObtenerDeclaracionSiniestro param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDeclaracionSiniestro");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Anio", param.getAnio());
		logueo.setParametro("Numero siniestro", param.getNumSiniestro());

		ResultObtenerDeclaracionSiniestro datos = new ResultObtenerDeclaracionSiniestro();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDeclaracionSiniestro(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDeclaracionSiniestro(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosSiniestro(uy.com
	 * .bse.polizas.consultas.ParamObtenerDatosSiniestro)
	 */
	public ResultObtenerDatosSiniestro obtenerDatosSiniestro(ParamObtenerDatosSiniestro param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosSiniestro");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Anio", param.getAnio());
		logueo.setParametro("Numero siniestro", param.getNumSiniestro());

		ResultObtenerDatosSiniestro datos = new ResultObtenerDatosSiniestro();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosSiniestro(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosSiniestro(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerSiniestrosCliente(uy
	 * .com.bse.polizas.consultas.ParamObtenerSiniestrosCliente)
	 */
	public ResultObtenerSiniestrosCliente obtenerSiniestrosCliente(ParamObtenerSiniestrosCliente param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerSiniestrosCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de cliente", param.getCodCliente());
		logueo.setParametro("Nacionalidad de cliente", param.getNacionalidadCliente());

		ResultObtenerSiniestrosCliente datos = new ResultObtenerSiniestrosCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerSiniestrosCliente(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerSiniestrosCliente(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerSiniestros(uy.com.bse
	 * .polizas.consultas.ParamObtenerSiniestros)
	 */
	public ResultObtenerSiniestros obtenerSiniestros(ParamObtenerSiniestros param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerSiniestros");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerSiniestros datos = new ResultObtenerSiniestros();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerSiniestros(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerSiniestros(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerSiniestrosConFiltro(
	 * uy.com.bse.polizas.consultas.ParamObtenerSiniestrosConFiltro)
	 */
	public ResultObtenerSiniestrosConFiltro obtenerSiniestrosConFiltro(ParamObtenerSiniestrosConFiltro param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerSiniestrosConFiltro");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha ocurrencia", param.getFechaOcurrencia());
		logueo.setParametro("Fecha entrada", param.getFechaEntrada());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo asegurado", param.getCodAsegurado());
		logueo.setParametro("Codigo ejecutivo", param.getCodEjecutivo());
		logueo.setParametro("Fuera pauta", param.getFueraPauta());
		logueo.setParametro("Asegurado", param.getAsegurado());
		logueo.setParametro("Descripcion ejecutivo", param.getDescripEjecutivo());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero siniestro", param.getNumSiniestro());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerSiniestrosConFiltro datos = new ResultObtenerSiniestrosConFiltro();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerSiniestrosConFiltro(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerSiniestrosConFiltro(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#validarPoliza(uy.com.bse.polizas
	 * .operaciones.ParamValidarPoliza)
	 */
	public ResultValidarPoliza validarPoliza(ParamValidarPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("validarPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultValidarPoliza datos = new ResultValidarPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarValidarPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().validarPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#validarAnulacionPoliza(uy.com
	 * .bse.polizas.operaciones.ParamValidarAnulacionPoliza)
	 */
	public ResultValidarAnulacionPoliza validarAnulacionPoliza(ParamValidarAnulacionPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("validarAnulacionPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Tipo de anulacion", param.getTipoAnulacion());
		logueo.setParametro("Fecha de anulacion", param.getFechaAnulacion());
		logueo.setParametro("Tiene facultativo", param.getTieneFacultativo());

		ResultValidarAnulacionPoliza datos = new ResultValidarAnulacionPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarValidarAnulacionPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().validarAnulacionPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#anularPoliza(uy.com.bse.polizas
	 * .operaciones.ParamAnularPoliza)
	 */
	public ResultAnularPoliza anularPoliza(ParamAnularPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("anularPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo motivo", param.getCodMotivoAbandono());
		logueo.setParametro("Aclaracion", param.getAclaracion());

		ResultAnularPoliza datos = new ResultAnularPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarAnularPoliza(param);

			if (error == null) {
				LOG.debug("ANULAR POLIZA 1 : valide correctamente el WS , antes de llamar al EJB");
				datos = WsServiciosPolizas.getEJBManager().anularPoliza(param);
				LOG.debug("ANULAR POLIZA 2 : despues de llamar al EJB");
				if (!datos.getHayError()) {
					LOG.debug("ANULAR POLIZA 3 : ANTES DE FACTURAR");
					StringBuffer sb = new StringBuffer("Hubo error en la generación de factura electrónica para factura(s) ");
					for (String factura : datos.getNumerosFacturas()) {
						ParamFacturacionElectronica paramFacturacion = new ParamFacturacionElectronica();
						paramFacturacion.setNumeroFactura(factura);
						paramFacturacion.setUsuario(param.getUsuario());
						paramFacturacion.setClave(param.getClave());
						
						logueo.setParametro("factura", paramFacturacion.getNumeroFactura());
						LOG.debug("ANULAR POLIZA 4 facturacion electronica EJB: ANTES DE EJB");
						ResultGenerico rfacturacion = WsServiciosPolizas.getEJBManager().facturacionElectronica(paramFacturacion);
						LOG.debug("ANULAR POLIZA 5 facturacion electronioca EJB: DESPUES DE EJB  DE FACTURAR");
						if (rfacturacion.getHayError()) {
							datos.setHayError(Boolean.TRUE);
							sb.append(factura);
							sb.append(", ");
						}
					}
					if (datos.getHayError()) {
						String errores = sb.toString();
						datos.setError(new ServiciosError(30, errores.substring(0, errores.length() - 2)));
					}
				}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaPlanesPagoRecuotificacion
	 * (uy.com.bse.recuotificacion.ParamListaPlanesPagoRec)
	 */
	public ResultListaPlanesPagoRec listaPlanesPagoRecuotificacion(ParamListaPlanesPagoRec param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaPlanesPagoRecuotificacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());

		ResultListaPlanesPagoRec datos = new ResultListaPlanesPagoRec();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaPlanesPagoRecuotificacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaPlanesPagoRecuotificacion(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#validarPolizaRecuotificacion
	 * (uy.com.bse.recuotificacion.ParamValidarPolizaRec)
	 */
	public ResultValidarPolizaRec validarPolizaRecuotificacion(ParamValidarPolizaRec param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("validarPolizaRecuotificacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultValidarPolizaRec datos = new ResultValidarPolizaRec();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarValidarPolizaRecuotificacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().validarPolizaRecuotificacion(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#calcularRecuotificacion(uy.
	 * com.bse.recuotificacion.ParamCalcularRec)
	 */
	public ResultCalcularRec calcularRecuotificacion(ParamCalcularRec param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("calcularRecuotificacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Fecha emision", param.getFechaEmision());
		logueo.setParametro("Codigo plan pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVencimiento());
		logueo.setParametro("Monto prima", param.getMontoPrima());
		logueo.setParametro("Monto premio", param.getMontoPremio());

		ResultCalcularRec datos = new ResultCalcularRec();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarCalcularRecuotificacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().calcularRecuotificacion(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#recuotificarPoliza(uy.com.bse
	 * .recuotificacion.ParamRecuotificarPoliza)
	 */
	public ResultRecuotificarPoliza recuotificarPoliza(ParamRecuotificarPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("recuotificarPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Fecha emision", param.getFechaEmision());
		logueo.setParametro("Codigo plan pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVencimiento());
		logueo.setParametro("Monto prima", param.getMontoPrima());
		logueo.setParametro("Monto premio", param.getMontoPremio());

		ResultRecuotificarPoliza datos = new ResultRecuotificarPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarRecuotificarPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().recuotificarPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaMotivoAbandono(uy.com.
	 * bse.polizas.consultas.ParamMotivoAbandono)
	 */
	public ResultMotivoAbandono listaMotivoAbandono(ParamMotivoAbandono param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaMotivoAbandono");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultMotivoAbandono datos = new ResultMotivoAbandono();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaMotivoAbandono(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaMotivoAbandono(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaOrigenEndoso(uy.com.bse
	 * .recuotificacion.ParamOrigenEndoso)
	 */
	public ResultOrigenEndoso listaOrigenEndoso(ParamOrigenEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaOrigenEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultOrigenEndoso datos = new ResultOrigenEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaOrigenEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaOrigenEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerConsolidadoPoliza(uy
	 * .com.bse.recuotificacion.ParamObtenerConsolidado)
	 */
	public ResultObtenerConsolidado obtenerConsolidadoPoliza(ParamObtenerConsolidado param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerConsolidadoPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerConsolidado datos = new ResultObtenerConsolidado();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerConsolidadoPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerConsolidadoPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#nuevaCotizacionEndoso(uy.com
	 * .bse.polizas.operaciones.ParamNuevaCotizacionEndoso)
	 */
	public ResultNuevaCotizacionEndoso nuevaCotizacionEndoso(ParamNuevaCotizacionEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("nuevaCotizacionEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Codigo de producto", param.getCodProducto());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo origen de endoso", param.getCodOrigenEndoso());
		logueo.setParametro("Fecha desde vigencia", param.getFechaDesdeVigencia());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());

		ResultNuevaCotizacionEndoso datos = new ResultNuevaCotizacionEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarNuevaCotizacionEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().nuevaCotizacionEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaTiposCarta(uy.com.bse.
	 * polizas.consultas.ParamTipoCarta)
	 */
	public ResultTipoCarta listaTiposCarta(ParamTipoCarta param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaTiposCarta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultTipoCarta datos = new ResultTipoCarta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaTiposCarta(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaTiposCarta(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaCartaXTipoCarta(uy.com
	 * .bse.polizas.consultas.ParamCartaXTipoCarta)
	 */
	public ResultCartaXTipoCarta listaCartaXTipoCarta(ParamCartaXTipoCarta param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaCartaXTipoCarta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultCartaXTipoCarta datos = new ResultCartaXTipoCarta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaCartaXTipoCarta(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaCartaXTipoCarta(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerValidarImpresionPoliza
	 * (uy.com.bse.polizas.consultas.ParamObtenerValidarImpresionPoliza)
	 */
	public ResultObtenerValidarImpresionPoliza obtenerValidarImpresionPoliza(ParamObtenerValidarImpresionPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerValidarImpresionPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerValidarImpresionPoliza datos = new ResultObtenerValidarImpresionPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerImpresionPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerValidarImpresionPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#guardarImprimirOrdenCarta(uy
	 * .com.bse.polizas.consultas.ParamGuardarImprimirCarta)
	 */
	public ResultGuardarImprimirOrdenCarta guardarImprimirOrdenCarta(ParamGuardarImprimirCarta param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("guardarImprimirOrdenCarta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Numero de carta", param.getNumCarta());
		logueo.setParametro("Numero de reporte", param.getNumReporte());
		logueo.setParametro("Codigo de reporte", param.getCodReporte());

		ResultGuardarImprimirOrdenCarta datos = new ResultGuardarImprimirOrdenCarta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarGuardarImprimirOrdenCarta(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().guardarImprimirOrdenCarta(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#guardarImprimirReporte(uy.com
	 * .bse.polizas.consultas.ParamGuardarImprimirReporte)
	 */
	public ResultGuardarImprimirReporte guardarImprimirReporte(ParamGuardarImprimirReporte param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("guardarImprimirReporte");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());
		logueo.setParametro("Items", param.getItems());

		ResultGuardarImprimirReporte datos = new ResultGuardarImprimirReporte();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarGuardarImprimirReporte(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().guardarImprimirReporte(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#agregarCertificadoEndoso(uy
	 * .com.bse.polizas.operaciones.ParamAgregarCertificadoEndoso)
	 */
	public ResultAgregarCertificadoEndoso agregarCertificadoEndoso(ParamAgregarCertificadoEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("agregarCertificadoEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());

		ResultAgregarCertificadoEndoso datos = new ResultAgregarCertificadoEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarAgregarCertificadoEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().agregarCertificadoEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosBasicosEndoso(uy
	 * .com.bse.polizas.consultas.ParamObtenerDatosBasicosEndoso)
	 */
	public ResultObtenerDatosBasicosEndoso obtenerDatosBasicosEndoso(ParamObtenerDatosBasicosEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosBasicosEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());

		ResultObtenerDatosBasicosEndoso datos = new ResultObtenerDatosBasicosEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosBasicosEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosBasicosEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaMotivosEndoso(uy.com.bse
	 * .polizas.consultas.ParamMotivoEndoso)
	 */
	public ResultMotivoEndoso listaMotivosEndoso(ParamMotivoEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaMotivosEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultMotivoEndoso datos = new ResultMotivoEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaMotivosEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaMotivosEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaDatosParametricosEndoso
	 * (uy.com.bse.polizas.consultas.ParamDatosParametricosEndoso)
	 */
	public ResultDatosParametricosEndoso listaDatosParametricosEndoso(ParamDatosParametricosEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaDatosParametricosEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Es ultimo endoso", param.getEsUltimoEndoso());

		ResultDatosParametricosEndoso datos = new ResultDatosParametricosEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaDatosParametricosEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaDatosParametricosEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaValoresDatosParametricosEndoso
	 * (uy.com.bse.polizas.consultas.ParamValoresDatosParametricosEndoso)
	 */
	public ResultValoresDatosParametricosEndoso listaValoresDatosParametricosEndoso(ParamValoresDatosParametricosEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaValoresDatosParametricosEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Es ultimo endoso", param.getEsUltimoEndoso());

		ResultValoresDatosParametricosEndoso datos = new ResultValoresDatosParametricosEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaValoresDatosParametricosEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaValoresDatosParametricosEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosXDatoParametricoEndoso
	 * (uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosEndoso)
	 */
	public ResultDatosXDatosParametricosEndoso obtenerDatosXDatoParametricoEndoso(ParamDatosXDatosParametricosEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosXDatoParametricoEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Codigo valor", param.getCodValor());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Ultimo endoso", param.getUltimoEndoso());

		ResultDatosXDatosParametricosEndoso datos = new ResultDatosXDatosParametricosEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosXDatoParametricoEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosXDatoParametricoEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#anularCertificadoEndoso(uy.
	 * com.bse.polizas.operaciones.ParamAnularCertificadoEndoso)
	 */
	public ResultAnularCertificadoEndoso anularCertificadoEndoso(ParamAnularCertificadoEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("anularCertificadoEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultAnularCertificadoEndoso datos = new ResultAnularCertificadoEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarAnularCertificadoEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().anularCertificadoEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaDatosParametricosPoliza
	 * (uy.com.bse.polizas.consultas.ParamDatosParametricoPoliza)
	 */
	public ResultDatosParametricoPoliza listaDatosParametricosPoliza(ParamDatosParametricoPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaDatosParametricosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		ResultDatosParametricoPoliza datos = new ResultDatosParametricoPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaDatosParametricosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaDatosParametricosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaValoresDatosParametricosPoliza
	 * (uy.com.bse.polizas.consultas.ParamValoresDatosParametricosPoliza)
	 */
	public ResultValoresDatosParametricosPoliza listaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaValoresDatosParametricosPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo dato", param.getCodDato());

		ResultValoresDatosParametricosPoliza datos = new ResultValoresDatosParametricosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaValoresDatosParametricosPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaValoresDatosParametricosPoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosXDatoParametricoPoliza
	 * (uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosPoliza)
	 */
	public ResultDatosXDatosParametricosPoliza obtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza entrada) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosXDatoParametricoPoliza");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo dato", entrada.getCodDato());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());
		logueo.setParametro("Valor dato", entrada.getValorDato());

		ResultDatosXDatosParametricosPoliza datos = new ResultDatosXDatosParametricosPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosXDatoParametricoPoliza(entrada);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosXDatoParametricoPoliza(entrada);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosEndosoSinPremio
	 * (uy.com.bse.polizas.consultas.ParamObtenerDatosEndosoSinPremio)
	 */
	public ResultObtenerDatosEndosoSinPremio obtenerDatosEndosoSinPremio(ParamObtenerDatosEndosoSinPremio entrada) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosEndosoSinPremio");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());

		ResultObtenerDatosEndosoSinPremio datos = new ResultObtenerDatosEndosoSinPremio();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosEndosoSinPremio(entrada);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosEndosoSinPremio(entrada);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#generarMarcaNoRenovar(uy.com
	 * .bse.polizas.consultas.ParamGenerarMarcaNoRenovar)
	 */
	public ResultGenerarMarcaNoRenovar generarMarcaNoRenovar(ParamGenerarMarcaNoRenovar entrada) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("generarMarcaNoRenovar");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());
		logueo.setParametro("Codigo de Origen", entrada.getCodOrigen());
		logueo.setParametro("Codigo Abandono", entrada.getCodMotAbandono());
		logueo.setParametro("Descripcion", entrada.getObsAbandono());

		ResultGenerarMarcaNoRenovar datos = new ResultGenerarMarcaNoRenovar();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.generarMarcaNoRenovar(entrada);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().generarMarcaNoRenovar(entrada);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#generarEndoso(uy.com.bse.polizas
	 * .operaciones.ParamGenerarEndoso)
	 */
	public ResultGenerarEndoso generarEndoso(ParamGenerarEndoso entrada) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("generarEndoso");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getNumRamo());

		ResultGenerarEndoso datos = new ResultGenerarEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.generarEndoso(entrada);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().generarEndoso(entrada);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#actualizarDatosParticulares
	 * (uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares)
	 */
	public ResultActualizarDatosParticulares actualizarDatosParticulares(ParamActualizarDatosParticulares entrada) {

		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("actualizarDatosParticulares");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getNumRamo());
		logueo.setParametro("Codigo bien", entrada.getCodBien());
		logueo.setParametro("Codigo dato", entrada.getCodDato());
		logueo.setParametro("Codigo ", entrada.getCodValor());

		ResultActualizarDatosParticulares datos = new ResultActualizarDatosParticulares();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.actualizarDatosParticulares(entrada);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().actualizarDatosParticulares(entrada);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#nuevaFacturacionInteractiva
	 * (uy.com.bse.polizas.operaciones.ParamNuevaFacturacionInteractiva)
	 */
	public ResultNuevaFacturacionInteractiva nuevaFacturacionInteractiva(ParamNuevaFacturacionInteractiva entrada) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("nuevaFacturacionInteractiva");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());
		logueo.setParametro("Fecha", entrada.getFecha());

		ResultNuevaFacturacionInteractiva datos = new ResultNuevaFacturacionInteractiva();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.nuevaFacturacionInteractiva(entrada);

			if (error == null) {
				LOG.debug("NUEVA Facturacion INTERACTIVA 1 : valide correctamente el WS , antes de llamar al EJB");
				datos = WsServiciosPolizas.getEJBManager().nuevaFacturacionInteractiva(entrada);
				LOG.debug("NUEVA Facturacion INTERACTIVA 2 : despues de llamar al EJB");
				if (!datos.getHayError()) {
					LOG.debug("NUEVA Facturacion INTERACTIVA  3 : ANTES DE FACTURAR");
					StringBuffer sb = new StringBuffer("Hubo error en la generación de factura electrónica para factura(s) ");
					for (String factura : datos.getNumerosFacturas()) {
						ParamFacturacionElectronica paramFacturacion = new ParamFacturacionElectronica();
						paramFacturacion.setNumeroFactura(factura);
						paramFacturacion.setUsuario(entrada.getUsuario());
						paramFacturacion.setClave(entrada.getClave());
						logueo.setParametro("factura", paramFacturacion.getNumeroFactura());
						LOG.debug("NUEVA Facturacion INTERACTIVA 4 facturacion electronica EJB: ANTES DE EJB");
						ResultFacturacionElectronica rfacturacion = WsServiciosPolizas.getEJBManager().facturacionElectronica(paramFacturacion);
						LOG.debug("NUEVA Facturacion INTERACTIVA 5 facturacion electronioca EJB: DESPUES DE EJB DE FACTURAR");
						if (rfacturacion.getHayError()) {
							datos.setHayError(Boolean.TRUE);
							sb.append(factura);
							sb.append(", ");
						}
						datos.setUnNumeroFacturaElectronica(rfacturacion.getDocumentId());
					}
					if (datos.getHayError()) {
						String errores = sb.toString();
						datos.setError(new ServiciosError(30, errores.substring(0, errores.length() - 2)));
					}
				}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#validarDetectarEndoso(uy.com
	 * .bse.recuotificacion.ParamValidarDetectarEndoso)
	 */
	public ResultValidarDetectarEndoso validarDetectarEndoso(ParamValidarDetectarEndoso param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("validarDetectarEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getNumRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Fecha desde", param.getFechaDesde());

		ResultValidarDetectarEndoso datos = new ResultValidarDetectarEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesPolizas validar = new ValidacionesPolizas();
			final ServiciosError error = validar.validarValidarDetectarEndoso(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().validarDetectarEndoso(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#modificacionSinPremio(uy.com
	 * .bse.recuotificacion.ParamModificacionSinPremio)
	 */
	public ResultModificacionSinPremio modificacionSinPremio(ParamModificacionSinPremio param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("modificacionSinPremio");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getNumRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Medio pago", param.getCodMedioPago());
		logueo.setParametro("Codigo origen endoso", param.getCodOrigenEndoso());

		ResultModificacionSinPremio datos = new ResultModificacionSinPremio();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesPolizas validar = new ValidacionesPolizas();
			final ServiciosError error = validar.validarModificacionSinPremio(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().modificacionSinPremio(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaAcreedorObjetos(uy.com
	 * .bse.polizas.consultas.ParamObjetosAcreedor)
	 */
	public ResultAcreedorObjetos listaAcreedorObjetos(ParamObjetosAcreedor param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaAcreedorObjetos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Cod de ramo", param.getCodRamo());
		logueo.setParametro("Numero de sucursal", param.getSucursal());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Codigo del bien del asegurado", param.getCodBienAsegurado());

		ResultAcreedorObjetos datos = new ResultAcreedorObjetos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesPolizas validar = new ValidacionesPolizas();
			final ServiciosError error = validar.validarListaAcreedorObjetos(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaAcreedorObjetos(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#cotizacionPendientePoliza(uy
	 * .com.bse.polizas.operaciones.ParamCotizacionPendiente)
	 */
	public ResultCotizacionPendiente cotizacionPendientePoliza(ParamCotizacionPendiente param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("cotizacionPendientePoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Cod de ramo", param.getCodRamo());

		ResultCotizacionPendiente datos = new ResultCotizacionPendiente();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesPolizas validar = new ValidacionesPolizas();
			final ServiciosError error = validar.validarCotizacionPendientePoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().cotizacionPendientePoliza(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#tienePermisoProducto(uy.com
	 * .bse.polizas.consultas.ParamTienePermisoProducto)
	 */
	public ResultCondicion tienePermisoProducto(ParamTienePermisoProducto param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("tienePermisoProducto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultCondicion datos = new ResultCondicion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarTienePermisoProducto(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().tienePermisoProducto(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#listaCertificadosParaEndosar
	 * (uy.com.bse.recuotificacion.ParamListaCertificadosEndosar)
	 */
	public ResultCodiguera listaCertificadosParaEndosar(ParamListaCertificadosEndosar param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("listaCertificadosParaEndosar");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarListaCertificadosParaEndosar(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().listaCertificadosParaEndosar(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosParametricos(uy
	 * .com.bse.polizas.consultas.ParamObtenerDatosParametricos)
	 */
	public ResultObtenerDatosParametricos obtenerDatosParametricos(ParamObtenerDatosParametricos param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosParametricos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Filtros", param.filtrosToString());

		ResultObtenerDatosParametricos datos = new ResultObtenerDatosParametricos();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosParametricos(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosParametricos(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerValoresXDatoParametrico
	 * (uy.com.bse.polizas.consultas.ParamObtenerValoresXDatoParametrico)
	 */
	public ResultCodiguera obtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerValoresXDatoParametrico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo Tabla", param.getCodTabla());

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerValoresXDatoParametrico(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerValoresXDatoParametrico(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerPolizasCabezal(uy.com
	 * .bse.polizas.consultas.ParamObtenerPolizasCabezal)
	 */
	public ResultObtenerPolizasCabezal obtenerPolizasCabezal(ParamObtenerPolizasCabezal param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerPolizasCabezal");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Fecha desde de la poliza", param.getFechaDesde());
		logueo.setParametro("Fecha de emision de la poliza", param.getFechaEmision());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Cliente", param.getCliente());
		logueo.setParametro("Asegurado", param.getAsegurado());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Fecha hasta de la poliza", param.getFechaHasta());
		logueo.setParametro("Vigente", param.getVigentes());
		logueo.setParametro("Orden", param.getOrden());
		logueo.setParametro("Codigo dato parametrico", param.getCodDatoParametrico());
		logueo.setParametro("Valor dato parametrico", param.getValorDatoParametrico());
		logueo.setParametro("Ultimo endoso", param.getUltimoEndoso());

		ResultObtenerPolizasCabezal datos = new ResultObtenerPolizasCabezal();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerPolizasCabezal(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerPolizasCabezal(param);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerPolizaCabezalDetalle
	 * (uy.com.bse.polizas.consultas.ParamObtenerPolizaCabezalDetalle)
	 */
	public ResultObtenerPolizaCabezalDetalle obtenerPolizaCabezalDetalle(ParamObtenerPolizaCabezalDetalle param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerPolizaCabezalDetalle");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		ResultObtenerPolizaCabezalDetalle datos = new ResultObtenerPolizaCabezalDetalle();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerPolizaCabezalDetalle(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerPolizaCabezalDetalle(param);
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

	/**
	 * Catch exception.
	 * 
	 * @param logueo
	 *            the logueo
	 * @param e
	 *            the e
	 * @return the string
	 */
	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		enviarMail(logueo);
		return claveError;
	}

	/**
	 * Finally block.
	 * 
	 * @param claveError
	 *            the clave error
	 * @param datos
	 *            the datos
	 */
	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}

	/**
	 * Gets the EJB manager.
	 * 
	 * @return the EJB manager
	 * @throws NamingException
	 *             the naming exception
	 */
	public static PolizasLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (PolizasLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Polizas!uy.com.bse.serviciosEJB.PolizasLocal");
	}

	/**
	 * Enviar mail.
	 * 
	 * @param mail
	 *            the mail
	 * @param ccMail
	 *            the cc mail
	 * @param logueo
	 *            the logueo
	 */
	// FIXME OIGRES... ESTO POR PRUEBAS... NO PONER EN PRODUCCION
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager = new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"), manager.obtenerValor("serviciosRector.ccmail.soporte"), logueo.getMensaje(),
					"SERVICIO POLIZAS Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.com.bse.polizas.ws.IWsServiciosPolizas#obtenerDatosFacturaDigital(
	 * uy.com.bse.polizas.consultas.ParamObtenerDatosFacturaDigital)
	 */
	public ResultObtenerDatosFacturaDigital obtenerDatosFacturaDigital(ParamObtenerDatosFacturaDigital param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("obtenerDatosFacturaDigital");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultObtenerDatosFacturaDigital datos = new ResultObtenerDatosFacturaDigital();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarObtenerDatosFacturaDigital(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().obtenerDatosFacturaDigital(param);
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
	
	
	public ResultCotizacionPendiente cotizacionPendientePolizaSinPremio(ParamCotizacionPendienteSinPremio param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("cotizacionPendientePolizaSinPremio");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultCotizacionPendiente datos = new ResultCotizacionPendiente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarCotizacionPendientePolizaSinPremio(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().cotizacionPendientePolizaSinPremio(param);
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
	
	/* (non-Javadoc)
	 * @see uy.com.bse.polizas.ws.IWsServiciosPolizas#enviarFacturaDigitalEMail(uy.com.bse.polizas.operaciones.ParamEnviarFacturaDigitalEMail)
	 */
	public ResultEnviarFacturaDigitalEMail enviarFacturaDigitalEMail(ParamEnviarFacturaDigitalEMail param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("enviarFacturaDigitalEMail");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Enviar factura", param.getEnviarFacturaDigitalEMail());
		logueo.setParametro("Consecutivo", param.getConsecutivo());
		logueo.setParametro("Comunicacion", param.getComunicacion());

		ResultEnviarFacturaDigitalEMail datos = new ResultEnviarFacturaDigitalEMail();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarEnviarFacturaDigitalEMail(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().enviarFacturaDigitalEMail(param);
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

	public ResultValidarRefacturacion validarRefacturacion (ParamValidarRefacturacion param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("validarRefacturacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultValidarRefacturacion datos = new ResultValidarRefacturacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.validarRefacturacion(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().validarRefacturacion(param);
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

	public ResultRefacturacionPoliza refacturarPoliza(ParamRefacturarPoliza param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosPolizas.class);
		logueo.setMetodo("validarRefacturacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultRefacturacionPoliza datos = new ResultRefacturacionPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesPolizas validar = new ValidacionesPolizas();
			ServiciosError error = validar.refacturarPoliza(param);

			if (error == null) {
				datos = WsServiciosPolizas.getEJBManager().refacturarPoliza(param);
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
