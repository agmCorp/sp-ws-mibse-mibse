package uy.com.bse.cotizaciones.operaciones.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizaciones.operaciones.ParamActualizarCertificadoCero;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCoberturaBien;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarDatos;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarDatosBancarios;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarDirCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamAgregarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ParamAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ParamAgregarCertificado;
import uy.com.bse.cotizaciones.operaciones.ParamAltaBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ParamAltaListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamAltaTextoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamAltaUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ParamAnularCertificado;
import uy.com.bse.cotizaciones.operaciones.ParamAnularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamBajaBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ParamBajaListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamBajaUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCertificado;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCobertura;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularValidarVigencia;
import uy.com.bse.cotizaciones.operaciones.ParamConfigurarFechasPromocion;
import uy.com.bse.cotizaciones.operaciones.ParamCopiarCotizaPoli;
import uy.com.bse.cotizaciones.operaciones.ParamEnviarCotizacionFueraPauta;
import uy.com.bse.cotizaciones.operaciones.ParamGuardarAsegurado;
import uy.com.bse.cotizaciones.operaciones.ParamGuardarCoberturas;
import uy.com.bse.cotizaciones.operaciones.ParamGuardarPlanes;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoBeneficiarios;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoBotones;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoUbicacion;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoVigenciaTecnica;
import uy.com.bse.cotizaciones.operaciones.ParamIngresarNotaPoliza;
import uy.com.bse.cotizaciones.operaciones.ParamModificarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ParamModificarBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ParamModificarListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamModificarOpcionesFacturacion;
import uy.com.bse.cotizaciones.operaciones.ParamModificarTextoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamModificarUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ParamNuevaCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamQuitarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ParamQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ParamRenovacionManual;
import uy.com.bse.cotizaciones.operaciones.ParamSetearPlanCobertura;
import uy.com.bse.cotizaciones.operaciones.ParamValidarAcumulaRiesgos;
import uy.com.bse.cotizaciones.operaciones.ParamValidarDatos;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarCertificadoCero;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarCoberturaBien;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarDatos;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarDatosBancarios;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarDirCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultAgregarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ResultAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ResultAgregarCertificado;
import uy.com.bse.cotizaciones.operaciones.ResultAltaBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ResultAltaListaBienes;
import uy.com.bse.cotizaciones.operaciones.ResultAltaTextoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultAltaUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ResultAnularCertificado;
import uy.com.bse.cotizaciones.operaciones.ResultAnularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultBajaBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ResultBajaListaBienes;
import uy.com.bse.cotizaciones.operaciones.ResultBajaUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCertificado;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCobertura;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularValidarVigencia;
import uy.com.bse.cotizaciones.operaciones.ResultConfigurarFechasPromocion;
import uy.com.bse.cotizaciones.operaciones.ResultCopiarCotizaPoli;
import uy.com.bse.cotizaciones.operaciones.ResultEnviarCotizacionFueraPauta;
import uy.com.bse.cotizaciones.operaciones.ResultGuardarAsegurado;
import uy.com.bse.cotizaciones.operaciones.ResultGuardarCoberturas;
import uy.com.bse.cotizaciones.operaciones.ResultGuardarPlanes;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoBeneficiarios;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoBotones;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoListaBienes;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoUbicacion;
import uy.com.bse.cotizaciones.operaciones.ResultHabilitoVigenciaTecnica;
import uy.com.bse.cotizaciones.operaciones.ResultIngresarNotaPoliza;
import uy.com.bse.cotizaciones.operaciones.ResultModificarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ResultModificarBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ResultModificarListaBienes;
import uy.com.bse.cotizaciones.operaciones.ResultModificarOpcionesFacturacion;
import uy.com.bse.cotizaciones.operaciones.ResultModificarTextoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultModificarUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ResultNuevaCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultQuitarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ResultQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ResultRenovacionManual;
import uy.com.bse.cotizaciones.operaciones.ResultValidarAcumulaRiesgos;
import uy.com.bse.cotizaciones.operaciones.ResultValidarDatos;
import uy.com.bse.cotizaciones.ws.WsCotizaciones;
import uy.com.bse.serviciosEJB.CotOperacionesLocal;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.cotizaciones.operaciones.ws.IWsCotOperaciones", serviceName = "WsCotOperaciones")
public class WsCotOperaciones implements IWsCotOperaciones {

	private static final Logger LOG = LogManager.getLogger(WsCotOperaciones.class);

	// Funcionalidades

	public ResultNuevaCotizacion nuevaCotizacion(ParamNuevaCotizacion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("nuevaCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Tipo de facturacion", param.getTipoFacturacion());
		logueo.setParametro("Codigo de moneda", param.getCodMoneda());
		logueo.setParametro("Medio de pago", param.getMedioPago());
		logueo.setParametro("Origen de pago", param.getOrigenPago());
		logueo.setParametro("Codigo de promocion", param.getCodPromocion());
		logueo.setParametro("Renovacion", param.getRenovacion());
		logueo.setParametro("Tipo de calculo", param.getTipoCalculo());
		logueo.setParametro("Vigencia", param.getVigencia());
		logueo.setParametro("Fecha desde", param.getFechaDesdeVigencia());
		logueo.setParametro("Fecha hasta", param.getFechaHastaVigencia());
		logueo.setParametro("Tipo de vigencia tecnica", param.getTipoVigenciaTecnica());
		logueo.setParametro("Anulacion corrida", param.getAnulacionCorrida());
		logueo.setParametro("Cliente contratante", param.getCodClienteContratante());
		logueo.setParametro("Tipo de copia", param.getTipoCopia());
		logueo.setParametro("Numero a copiar", param.getNumACopiar());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());

		ResultNuevaCotizacion datos = new ResultNuevaCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarNuevaCotizacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().nuevaCotizacion(param);
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

	public ResultCopiarCotizaPoli copiarCotizaPoli(ParamCopiarCotizaPoli param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("copiarCotizaPoli");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Cotizacion a copiar", param.getNumCotizacionACopiar());
		logueo.setParametro("Poliza a copiar", param.getNumPolizaACopiar());

		ResultCopiarCotizaPoli datos = new ResultCopiarCotizaPoli();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarCopiarCotizaPoli(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().copiarCotizaPoli(param);
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

	public ResultAnularCertificado anularCertificado(ParamAnularCertificado param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("anularCertificado");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Consecutivo", param.getNumConsecutivo());

		ResultAnularCertificado datos = new ResultAnularCertificado();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAnularCertificado(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().anularCertificado(param);
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

	public ResultAgregarBien agregarBien(ParamAgregarBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("agregarBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Consecutivo", param.getNumConsecutivo());
		logueo.setParametro("Codigo de bien", param.getCodBien());

		ResultAgregarBien datos = new ResultAgregarBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAgregarBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().agregarBien(param);
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

	public ResultAgregarCertificado agregarCertificado(ParamAgregarCertificado param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("agregarCertificado");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultAgregarCertificado datos = new ResultAgregarCertificado();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAgregarCertificado(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().agregarCertificado(param);
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

	public ResultQuitarBien quitarBien(ParamQuitarBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("quitarBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultQuitarBien datos = new ResultQuitarBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarQuitarBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().quitarBien(param);
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

	public ResultAnularCotizacion anularCotizacion(ParamAnularCotizacion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("anularCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultAnularCotizacion datos = new ResultAnularCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAnularCotizacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().anularCotizacion(param);
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

	public ResultHabilitoAgregarBien habilitoAgregarBien(ParamHabilitoAgregarBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("habilitoAgregarBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());

		ResultHabilitoAgregarBien datos = new ResultHabilitoAgregarBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoAgregarBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoAgregarBien(param);
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

	public ResultHabilitoQuitarBien habilitoQuitarBien(ParamHabilitoQuitarBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("habilitoQuitarBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		ResultHabilitoQuitarBien datos = new ResultHabilitoQuitarBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoQuitarBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoQuitarBien(param);
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

	public ResultHabilitoListaBienes habilitoListaBienes(ParamHabilitoListaBienes param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("habilitoListaBienes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		ResultHabilitoListaBienes datos = new ResultHabilitoListaBienes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoListaBienes(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoListaBienes(param);
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

	public ResultHabilitoUbicacion habilitoUbicacion(ParamHabilitoUbicacion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("habilitoUbicacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		ResultHabilitoUbicacion datos = new ResultHabilitoUbicacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoUbicacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoUbicacion(param);
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

	public ResultHabilitoBeneficiarios habilitoBeneficiarios(ParamHabilitoBeneficiarios param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("habilitoBeneficiarios");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		ResultHabilitoBeneficiarios datos = new ResultHabilitoBeneficiarios();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoBeneficiarios(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoBeneficiarios(param);
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

	public ResultAltaUbicacionBien altaUbicacionBien(ParamAltaUbicacionBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("altaUbicacionBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());
		logueo.setParametro("Numero postal", param.getNumPostal());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("Numero calle", param.getNumero());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("Departamento", param.getDpto());
		logueo.setParametro("Numero fax", param.getFax());
		logueo.setParametro("Numero telefono", param.getTel());
		logueo.setParametro("Numero padron", param.getNumPadron());

		ResultAltaUbicacionBien datos = new ResultAltaUbicacionBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAltaUbicacionBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().altaUbicacionBien(param);
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

	public ResultBajaUbicacionBien bajaUbicacionBien(ParamBajaUbicacionBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("bajaUbicacionBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());

		ResultBajaUbicacionBien datos = new ResultBajaUbicacionBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarBajaUbicacionBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().bajaUbicacionBien(param);
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

	public ResultModificarUbicacionBien modificarUbicacionBien(ParamModificarUbicacionBien param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("modificarUbicacionBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());
		logueo.setParametro("Numero consecutivo ubicacion", param.getConsecutivoUbicacion());
		logueo.setParametro("Numero postal", param.getNumPostal());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("Numero calle", param.getNumero());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("Departamento", param.getDpto());
		logueo.setParametro("Numero fax", param.getFax());
		logueo.setParametro("Numero telefono", param.getTel());
		logueo.setParametro("Numero padron", param.getNumPadron());

		ResultModificarUbicacionBien datos = new ResultModificarUbicacionBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarModificarUbicacionBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().modificarUbicacionBien(param);
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

	public ResultValidarDatos validarDatos(ParamValidarDatos param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("validarDatos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero posicion bien", param.getPosicionBien());

		ResultValidarDatos datos = new ResultValidarDatos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarValidarDatos(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().validarDatos(param);
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

	public ResultActualizarDatos actualizarDatos(ParamActualizarDatos param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("actualizarDatos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo dato parametrico", param.getDatoCod());
		logueo.setParametro("Consecutivo del  bien", param.getNumConsBien());
		logueo.setParametro("Valor del dato", param.getDatoValor());

		ResultActualizarDatos datos = new ResultActualizarDatos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarActualizarDatos(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().actualizarDatos(param);
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

	public ResultGuardarCoberturas guardarCoberturas(ParamGuardarCoberturas param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("guardarCoberturas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo plan cobertura", param.getCodPlanCobertura());

		ResultGuardarCoberturas datos = new ResultGuardarCoberturas();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarGuardarCoberturas(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().guardarCoberturas(param);
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

	public ResultCalcularCotizacion calcularCotizacion(ParamCalcularCotizacion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("calcularCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo plan pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVencimiento());

		ResultCalcularCotizacion datos = new ResultCalcularCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarCalcularCotizacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().calcularCotizacion(param);
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

	public ResultHabilitoVigenciaTecnica habilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("habilitoVigenciaTecnica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Tipo vigencia", param.getTipoVigencia());

		ResultHabilitoVigenciaTecnica datos = new ResultHabilitoVigenciaTecnica();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoVigenciaTecnica(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoVigenciaTecnica(param);
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

	public ResultAltaBeneficiario altaBeneficiario(ParamAltaBeneficiario param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("altaBeneficiario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Nombre beneficiario", param.getBeneficiario());
		logueo.setParametro("Codigo parentesco", param.getCodParentesco());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Fecha de nacimiento", param.getFechaNacimiento());
		logueo.setParametro("Observaciones", param.getObservaciones());

		ResultAltaBeneficiario datos = new ResultAltaBeneficiario();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAltaBeneficiario(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().altaBeneficiario(param);
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

	public ResultModificarBeneficiario modificarBeneficiario(ParamModificarBeneficiario param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("modificarBeneficiario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Tipo documento referente", param.getTipoDocumentoReferente());
		logueo.setParametro("Numero documento referente", param.getNumDocumentoReferente());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Nombre beneficiario", param.getBeneficiario());
		logueo.setParametro("Codigo parentesco", param.getCodParentesco());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Fecha de nacimiento", param.getFechaNacimiento());
		logueo.setParametro("Observaciones", param.getObservaciones());

		ResultModificarBeneficiario datos = new ResultModificarBeneficiario();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarModificarBeneficiario(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().modificarBeneficiario(param);
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

	public ResultBajaBeneficiario bajaBeneficiario(ParamBajaBeneficiario param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("bajaBeneficiario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Tipo documento referente", param.getTipoDocumentoReferente());
		logueo.setParametro("Numero documento referente", param.getNumDocumentoReferente());
		logueo.setParametro("Fecha exclusion", param.getFechaExclusion());

		ResultBajaBeneficiario datos = new ResultBajaBeneficiario();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarBajaBeneficiario(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().bajaBeneficiario(param);
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

	public ResultAltaListaBienes altaListaBienes(ParamAltaListaBienes param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("altaListaBienes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo objeto", param.getCodObjeto());
		logueo.setParametro("Descripcion", param.getDescripcion());
		logueo.setParametro("Valor", param.getValor());
		logueo.setParametro("Numero unidades", param.getNumUnidades());
		logueo.setParametro("Codigo marca", param.getCodMarca());
		logueo.setParametro("Serial", param.getSerial());

		ResultAltaListaBienes datos = new ResultAltaListaBienes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAltaListaBienes(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().altaListaBienes(param);
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

	public ResultBajaListaBienes bajaListaBienes(ParamBajaListaBienes param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("bajaListaBienes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Numero ordinal", param.getNumOrdinal());

		ResultBajaListaBienes datos = new ResultBajaListaBienes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarBajaListaBienes(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().bajaListaBienes(param);
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

	public ResultModificarListaBienes modificarListaBienes(ParamModificarListaBienes param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("modificarListaBienes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo objeto", param.getCodObjeto());
		logueo.setParametro("Descripcion", param.getDescripcion());
		logueo.setParametro("Valor", param.getValor());
		logueo.setParametro("Numero unidades", param.getNumUnidades());
		logueo.setParametro("Codigo marca", param.getCodMarca());
		logueo.setParametro("Serial", param.getSerial());
		logueo.setParametro("Numero ordinal", param.getNumOrdinal());

		ResultModificarListaBienes datos = new ResultModificarListaBienes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarModificarListaBienes(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().modificarListaBienes(param);
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

	public ResultRenovacionManual renovacionManual(ParamRenovacionManual param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("renovacionManual");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo productor", param.getCodProductor());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Renovacion", param.getRenovacion());
		logueo.setParametro("Tipo calculo", param.getTipoCalculo());
		logueo.setParametro("Forma pago", param.getFormaPago());
		logueo.setParametro("Fecha hasta vigencia", param.getFechaHastaVigencia());
		logueo.setParametro("Plan pago", param.getPlanPago());
		logueo.setParametro("Medio pago", param.getMedioPago());
		logueo.setParametro("Origen pago", param.getOrigenPago());
		logueo.setParametro("Codigo banco", param.getCodBanco());
		logueo.setParametro("Solo cotiza", param.getSoloCotiza());

		ResultRenovacionManual datos = new ResultRenovacionManual();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarRenovacionManual(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().renovacionManual(param);
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

	public ResultIngresarNotaPoliza ingresarNotaPoliza(ParamIngresarNotaPoliza param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ingresarNotaPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo nota", param.getCodTipoNota());
		logueo.setParametro("Enlace", param.getEnlace());
		logueo.setParametro("Observaciones", param.getObservaciones());
		logueo.setParametro("Valor 1", param.getValor1());
		logueo.setParametro("Valor 2", param.getValor2());

		ResultIngresarNotaPoliza datos = new ResultIngresarNotaPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarIngresarNotaPoliza(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().ingresarNotaPoliza(param);
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

	public ResultHabilitoBotones habilitoBotones(ParamHabilitoBotones param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("habilitoBotones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		ResultHabilitoBotones datos = new ResultHabilitoBotones();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarHabilitoBotones(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().habilitoBotones(param);
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

	public ResultValidarAcumulaRiesgos validarAcumulaRiesgos(ParamValidarAcumulaRiesgos param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("validarAcumulaRiesgos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultValidarAcumulaRiesgos datos = new ResultValidarAcumulaRiesgos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			final ServiciosError error = validar.validarAcumulaRiesgos(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().validarAcumulaRiesgos(param);
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

	public ResultAltaTextoCotizacion altaTextoCotizacion(ParamAltaTextoCotizacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("altaTextoCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo de texto", param.getCodTexto());
		logueo.setParametro("Impresion", param.getImpresion());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Detalle texto", param.getDetalleTexto());
		logueo.setParametro("Persiste", param.getPersiste());

		ResultAltaTextoCotizacion datos = new ResultAltaTextoCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarAltaTextoCotizacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().altaTextoCotizacion(param);
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

	public ResultModificarTextoCotizacion modificarTextoCotizacion(ParamModificarTextoCotizacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("modificarTextoCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo texto", param.getNumConsecutivoTexto());
		logueo.setParametro("Codigo de texto", param.getCodTexto());
		logueo.setParametro("Impresion", param.getImpresion());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Detalle texto", param.getDetalleTexto());
		logueo.setParametro("Persiste", param.getPersiste());

		ResultModificarTextoCotizacion datos = new ResultModificarTextoCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarModificarTextoCotizacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().modificarTextoCotizacion(param);
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

	public ResultAgregarAcreedorXBien agregarAcreedorXBien(ParamAgregarAcreedorXBien param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("agregarAcreedorXBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo de acreedor", param.getCodAcreedor());
		logueo.setParametro("Codigo de objeto", param.getCodObjeto());
		logueo.setParametro("Tipo acreedor", param.getTipoAcreedor());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());

		ResultAgregarAcreedorXBien datos = new ResultAgregarAcreedorXBien();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarAgregarAcreedorXBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().agregarAcreedorXBien(param);
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

	public ResultModificarAcreedorXBien modificarAcreedorXBien(ParamModificarAcreedorXBien param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("modificarAcreedorXBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo de acreedor", param.getCodAcreedor());
		logueo.setParametro("Codigo de objeto", param.getCodObjeto());
		logueo.setParametro("Tipo acreedor", param.getTipoAcreedor());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Identificador", param.getIdentificador());

		ResultModificarAcreedorXBien datos = new ResultModificarAcreedorXBien();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarModificarAcreedorXBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().modificarAcreedorXBien(param);
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

	public ResultQuitarAcreedorXBien quitarAcreedorXBien(ParamQuitarAcreedorXBien param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("quitarAcreedorXBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Identificador", param.getIdentificador());

		ResultQuitarAcreedorXBien datos = new ResultQuitarAcreedorXBien();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarQuitarAcreedorXBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().quitarAcreedorXBien(param);
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

	public ResultCalcularValidarVigencia calcularValidarVigencia(ParamCalcularValidarVigencia param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("calcularValidarVigencia");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de promocion", param.getCodPromocion());
		logueo.setParametro("Modo de calculo", param.getModoCalculo());
		logueo.setParametro("Vigencia", param.getVigencia());
		logueo.setParametro("Tipo de transaccion", param.getTipoTransaccion());
		logueo.setParametro("Fecha desde", param.getFechaDesdeVigencia());
		logueo.setParametro("Fecha hasta", param.getFechaHastaVigencia());
		logueo.setParametro("Codigo de vigencia tecnica", param.getCodVigenciaTecnica());

		ResultCalcularValidarVigencia datos = new ResultCalcularValidarVigencia();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarCalcularValidarVigencia(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().calcularValidarVigencia(param);
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

	public ResultGuardarPlanes guardarPlanes(ParamGuardarPlanes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("guardarPlanes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		for (int i = 0; i < param.getPlanesCotizacion().size(); i++) {
			logueo.setParametro("Certificado" + i, param.getPlanesCotizacion().get(i).getNumCertificado());
			logueo.setParametro("PlanCobertura" + i, param.getPlanesCotizacion().get(i).getCodPlanCobertura());
			logueo.setParametro("PlanPago" + i, param.getPlanesCotizacion().get(i).getCodPlanPago());
		}

		ResultGuardarPlanes datos = new ResultGuardarPlanes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarGuardarPlanes(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().guardarPlanes(param);
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

	public ResultActualizarDirCotizacion actualizarDireccionesCotizacion(ParamActualizarDirCotizacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("actualizarDireccionesCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());

		ResultActualizarDirCotizacion datos = new ResultActualizarDirCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarActualizarDireccionesCotizacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().actualizarDireccionesCotizacion(param);
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

	public ResultActualizarCoberturaBien actualizarCoberturaBien(ParamActualizarCoberturaBien param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("actualizarCoberturaBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Consecutivo bien", param.getNumConsecutivo());
		logueo.setParametro("Codigo plan cobertura", param.getCodPlanCobertura());
		logueo.setParametro("Codigo ramo cobertura", param.getCodRamoCobertura());
		logueo.setParametro("Codigo cobertura", param.getCodCobertura());
		logueo.setParametro("Valor", param.getValor());

		ResultActualizarCoberturaBien datos = new ResultActualizarCoberturaBien();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarActualizarCoberturaBien(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().actualizarCoberturaBien(param);
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

	public ResultCalcularCobertura calcularCobertura(ParamCalcularCobertura param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("calcularCobertura");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo plan cobertura", param.getCodPlanCobertura());

		ResultCalcularCobertura datos = new ResultCalcularCobertura();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarCalcularCobertura(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().calcularCobertura(param);
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

	public ResultCalcularCertificado calcularCertificado(ParamCalcularCertificado param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("calcularCertificado");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Plan cobertura", param.getPlanCobertura());
		logueo.setParametro("Plan pago", param.getPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVto());
		logueo.setParametro("Requeridos Planes Cobertura", param.getRequeridoPlanesCobertura());
		logueo.setParametro("Nivel de comision productor",param.getCodNivelComisionProductor());
		logueo.setParametro("Nivel de comision broker",param.getCodNivelComisionProductor());
		

		ResultCalcularCertificado datos = new ResultCalcularCertificado();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarCalcularCertificado(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().calcularCertificado(param);
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

	public ResultGuardarAsegurado guardarAsegurado(ParamGuardarAsegurado param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("guardarAsegurado");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo cliente", param.getCodCliente());

		ResultGuardarAsegurado datos = new ResultGuardarAsegurado();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarGuardarAsegurado(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().guardarAsegurado(param);
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

	public ResultActualizarCertificadoCero actualizarCertificadoCero(ParamActualizarCertificadoCero param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("actualizarCertificadoCero");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Tipo facturacion", param.getTipoFacturacion());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Medio pago", param.getMedioPago());
		logueo.setParametro("Origen pago", param.getOrigenPago());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Renovacion", param.getRenovacion());
		logueo.setParametro("Tipo calculo", param.getTipoCalculo());
		logueo.setParametro("Vigencia", param.getVigencia());
		logueo.setParametro("Fecha desde vigencia", param.getFechaDesdeVigencia());
		logueo.setParametro("Fecha hasta vigencia", param.getFechaHastaVigencia());
		logueo.setParametro("Tipo vigencia tecnica", param.getTipoVigenciaTecnica());
		logueo.setParametro("Anulacion corrida", param.getAnulacionCorrida());
		logueo.setParametro("Codigo cliente", param.getCodClienteContratante());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());
		logueo.setParametro("Fecha desde vigencia tecnica", param.getFechaDesdeVigenciaTecnica());
		logueo.setParametro("Fecha hasta vigencia tecnica ", param.getFechaHastaVigenciaTecnica());

		ResultActualizarCertificadoCero datos = new ResultActualizarCertificadoCero();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarActualizarCertificadoCero(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().actualizarCertificadoCero(param);
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

	public ResultGenerico setearPlanCobertura(ParamSetearPlanCobertura param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("setearPlanCobertura");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Plan cobertura", param.getPlanCobertura());

		ResultGenerico datos = new ResultCalcularCertificado();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarSetearPlanCobertura(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().setearPlanCobertura(param);
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

	public ResultActualizarDatosBancarios actualizarDatosBancarios(ParamActualizarDatosBancarios param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("actualizarDatosBancarios");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero domicilio banco", param.getNumDomicilioBanco());

		ResultActualizarDatosBancarios datos = new ResultActualizarDatosBancarios();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarActualizarDatosBancarios(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().actualizarDatosBancarios(param);
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

	public ResultEnviarCotizacionFueraPauta enviarCotizacionFueraPauta(ParamEnviarCotizacionFueraPauta param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("enviarCotizacionFueraPauta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		ResultEnviarCotizacionFueraPauta datos = new ResultEnviarCotizacionFueraPauta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarEnviarCotizacionFueraPauta(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().enviarCotizacionFueraPauta(param);
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

	public ResultModificarOpcionesFacturacion modificarOpcionesFacturacion(ParamModificarOpcionesFacturacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("modificarOpcionesFacturacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Enviar Factura por email", param.getEnviarFacturaEmail());
		logueo.setParametro("Emitir con RUT", param.getEmitirConRUT());

		ResultModificarOpcionesFacturacion datos = new ResultModificarOpcionesFacturacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarModificarOpcionesFacturacion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().modificarOpcionesFacturacion(param);
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

	
	public ResultConfigurarFechasPromocion configurarFechasPromocion(ParamConfigurarFechasPromocion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotOperaciones.class);
		logueo.setMetodo("configurarFechasPromocion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		
		ResultConfigurarFechasPromocion datos = new ResultConfigurarFechasPromocion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotOperaciones validar = new ValidacionesCotOperaciones();
			ServiciosError error = validar.validarConfigurarFechasPromocion(param);

			if (error == null) {
				datos = WsCotOperaciones.getEJBManager().configurarFechasPromocion(param);
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

	public static CotOperacionesLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (CotOperacionesLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/CotOperaciones!uy.com.bse.serviciosEJB.CotOperacionesLocal");
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
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO COTOPERACIONES Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}


}