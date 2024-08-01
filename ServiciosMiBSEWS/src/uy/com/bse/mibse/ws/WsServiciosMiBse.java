package uy.com.bse.mibse.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.MiBseLocal;
import uy.com.bse.mibse.DatosComunicacion;
import uy.com.bse.mibse.ParamActualizarComunicacionesCliente;
import uy.com.bse.mibse.ParamActualizarDatosCliente;
import uy.com.bse.mibse.ParamActualizarFacturacionPoliza;
import uy.com.bse.mibse.ParamActualizarFacturacionPolizaTodo;
import uy.com.bse.mibse.ParamAdherirFacturaDigital;
import uy.com.bse.mibse.ParamAltaMailCliente;
import uy.com.bse.mibse.ParamBorrarMailCliente;
import uy.com.bse.mibse.ParamCorrespondeCartaPoliza;
import uy.com.bse.mibse.ParamExisteCliente;
import uy.com.bse.mibse.ParamInformarPagoBancario;
import uy.com.bse.mibse.ParamInformarPagoRedes;
import uy.com.bse.mibse.ParamListaProfesiones;
import uy.com.bse.mibse.ParamListaTipoDocumentos;
import uy.com.bse.mibse.ParamLogActividadMibseWsExt;
import uy.com.bse.mibse.ParamModificarMailCliente;
import uy.com.bse.mibse.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.ParamObtenerDatosCliente;
import uy.com.bse.mibse.ParamObtenerDatosValidadosCliente;
import uy.com.bse.mibse.ParamObtenerMailCliente;
import uy.com.bse.mibse.ParamObtenerMailsEnvioFacturaCliente;
import uy.com.bse.mibse.ParamObtenerMapaMsgSiniestro;
import uy.com.bse.mibse.ParamObtenerMaximoEndoso;
import uy.com.bse.mibse.ParamObtenerNumeroCliente;
import uy.com.bse.mibse.ParamObtenerPolizasCliente;
import uy.com.bse.mibse.ParamObtenerPolizasFacturasPagasCliente;
import uy.com.bse.mibse.ParamOlvidoClave;
import uy.com.bse.mibse.ParamProCarta;
import uy.com.bse.mibse.ParamProCarta2;
import uy.com.bse.mibse.ParamRegistrarCliente;
import uy.com.bse.mibse.ParamSubirArchivo;
import uy.com.bse.mibse.ParamValidacionCartaVerde;
import uy.com.bse.mibse.ParamValidacionSOA;
import uy.com.bse.mibse.ParamValidarCertificadoLibreDeudaADT;
import uy.com.bse.mibse.ParamValidarCodigoAdhesion;
import uy.com.bse.mibse.ResultAdherirFacturaDigital;
import uy.com.bse.mibse.ResultCorrespondeCartaPoliza;
import uy.com.bse.mibse.ResultExisteCliente;
import uy.com.bse.mibse.ResultInformarPagoBancario;
import uy.com.bse.mibse.ResultInformarPagoRedes;
import uy.com.bse.mibse.ResultLogActividadMibseWsExt;
import uy.com.bse.mibse.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.ResultObtenerDatosCliente;
import uy.com.bse.mibse.ResultObtenerDatosValidadosCliente;
import uy.com.bse.mibse.ResultObtenerMailCliente;
import uy.com.bse.mibse.ResultObtenerMailsEnvioFacturaCliente;
import uy.com.bse.mibse.ResultObtenerMapaMsgSiniestro;
import uy.com.bse.mibse.ResultObtenerMaximoEndoso;
import uy.com.bse.mibse.ResultObtenerNumeroCliente;
import uy.com.bse.mibse.ResultObtenerPolizasCliente;
import uy.com.bse.mibse.ResultObtenerPolizasFacturasPagasCliente;
import uy.com.bse.mibse.ResultProCarta;
import uy.com.bse.mibse.ResultRegistrarCliente;
import uy.com.bse.mibse.ResultSubirArchivo;
import uy.com.bse.mibse.ResultValidacionCartaVerde;
import uy.com.bse.mibse.ResultValidacionSOA;
import uy.com.bse.mibse.ResultValidarCertificadoLibreDeudaADT;
import uy.com.bse.mibse.ResultValidarCodigoAdhesion;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.mibse.ws.IWsServiciosMiBse", serviceName = "WsServiciosMiBse")
public class WsServiciosMiBse implements IWsServiciosMiBse {

	private static Logger LOG = LogManager.getLogger(WsServiciosMiBse.class);

	public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerComunicacionesCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultObtenerComunicacionesCliente datos = new ResultObtenerComunicacionesCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerComunicacionesCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerComunicacionesCliente(param);
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

	public ResultObtenerPolizasCliente obtenerPolizasCliente(ParamObtenerPolizasCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerComunicacionesCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultObtenerPolizasCliente datos = new ResultObtenerPolizasCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerPolizasCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerPolizasCliente(param);
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

	public ResultGenerico actualizarFacturacionPoliza(ParamActualizarFacturacionPoliza param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("actualizarFacturacionPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Validada", param.getCodMarca());
		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarActualizarFacturacionPoliza(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().actualizarFacturacionPoliza(param);
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

	public ResultObtenerDatosCliente obtenerDatosCliente(ParamObtenerDatosCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerDatosCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultObtenerDatosCliente datos = new ResultObtenerDatosCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerDatosCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerDatosCliente(param);
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

	public ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerNumeroCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultObtenerNumeroCliente datos = new ResultObtenerNumeroCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerNumeroCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerNumeroCliente(param);
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

	public ResultValidarCodigoAdhesion validarCodigoAdhesion(ParamValidarCodigoAdhesion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("validarCodigoAdhesion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultValidarCodigoAdhesion datos = new ResultValidarCodigoAdhesion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarValidarCodigoAdhesion(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().validarCodigoAdhesion(param);
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

	public ResultGenerico actualizarComunicacionesCliente(ParamActualizarComunicacionesCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("actualizarComunicacionesCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		if (param.getComunicaciones() != null) {
			for (DatosComunicacion dato : param.getComunicaciones()) {
				logueo.setParametro("Codigo comunicacion", dato.getCodComunicacion());
				logueo.setParametro("Tipo comunicacion", dato.getTipoComunicacion());
				logueo.setParametro("Validada", dato.getValidada());
				logueo.setParametro("Valor comunicacion", dato.getValorComunicacion());
			}
		}
		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarActualizarComunicacionesCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().actualizarComunicacionesCliente(param);
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

	public ResultGenerico actualizarFacturacionPolizaTodo(ParamActualizarFacturacionPolizaTodo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("actualizarFacturacionPolizaTodo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarActualizarFacturacionPolizaTodo(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().actualizarFacturacionPolizaTodo(param);
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

	public ResultGenerico actualizarDatosCliente(ParamActualizarDatosCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("actualizarDatosCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("Codigo profesion", param.getCodProfesion());
		logueo.setParametro("Sexo", param.getSexo());
		logueo.setParametro("Fecha nacimiento", param.getFechaNacimiento());
		logueo.setParametro("Numero persona", param.getNumPersona());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());

		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarActualizarDatosCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().actualizarDatosCliente(param);
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

	public ResultRegistrarCliente registrarCliente(ParamRegistrarCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("registrarCliente");
		logueo.setParametro("Codigo Adhesion", param.getCodigoAdhesion());
		logueo.setParametro("Num Documento", param.getNumDocumento());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());

		ResultRegistrarCliente datos = new ResultRegistrarCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarRegistroCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().registrarCliente(param);
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

	public ResultCodiguera listaProfesiones(ParamListaProfesiones param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("listaProfesiones");

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			datos = WsServiciosMiBse.getEJBManager().listaProfesiones(param);
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultCodiguera listaTipoDocumentos(ParamListaTipoDocumentos param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("listaTipoDocumentos");

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			datos = WsServiciosMiBse.getEJBManager().listaTipoDocumentos(param);
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerMailCliente obtenerSeudoMailCliente(ParamObtenerMailCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerSeudoMailCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultObtenerMailCliente datos = new ResultObtenerMailCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerSeudoMailCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerSeudoMailCliente(param);
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

	public ResultGenerico olvidoClave(ParamOlvidoClave param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("olvidoClave");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarOlvidoClave(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().olvidoClave(param);
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

	public ResultObtenerDatosValidadosCliente obtenerDatosValidadosCliente(ParamObtenerDatosValidadosCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerDatosValidadosCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerDatosValidadosCliente datos = new ResultObtenerDatosValidadosCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerDatosValidadosCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerDatosValidadosCliente(param);
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

	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(), e);
		enviarMail(logueo);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}

	public static MiBseLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (MiBseLocal) ctx.lookup("global/Mibse/MiBSEEJB/MiBse!uy.com.bse.MiBseLocal");
	}

	// FIXME OIGRES... ESTO POR PRUEBAS... NO PONER EN PRODUCCION
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager = new EARPropertiesManager("configMibse.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("mibse.mail.soporte"),
					manager.obtenerValor("mibse.ccmail.soporte"), logueo.getMensaje(),
					"SERVICIO MIBSE Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.debug("No se encontro el nombre de Host", e);
		}
	}

	@Override
	public ResultInformarPagoBancario informarPagoBancario(ParamInformarPagoBancario param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("informarPagoBancario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());
		logueo.setParametro("codigoAutorizacion", param.getCodigoAutorizacion());
		logueo.setParametro("documentId", param.getDocumentId());
		logueo.setParametro("codProducto", param.getCodProducto());
		logueo.setParametro("descProducto", param.getDescProducto());
		logueo.setParametro("codRamo", param.getCodRamo());
		logueo.setParametro("descRamo", param.getDescRamo());
		logueo.setParametro("numPoliza", param.getNumPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("tipoDocumento", param.getTipoDocumento());
		logueo.setParametro("nroDocumento", param.getNroDocumento());
		logueo.setParametro("nombres", param.getNombres());
		logueo.setParametro("apellidos", param.getApellidos());

		ResultInformarPagoBancario datos = new ResultInformarPagoBancario();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarInformarPagoBancario(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().informarPagoBancario(param);
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

	@Override
	public ResultInformarPagoRedes informarPagoRedes(ParamInformarPagoRedes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("informarPagoRedes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());
		logueo.setParametro("documentId", param.getDocumentId());
		logueo.setParametro("codProducto", param.getCodProducto());
		logueo.setParametro("descProducto", param.getDescProducto());
		logueo.setParametro("codRamo", param.getCodRamo());
		logueo.setParametro("descRamo", param.getDescRamo());
		logueo.setParametro("numPoliza", param.getNumPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("tipoDocumento", param.getTipoDocumento());
		logueo.setParametro("nroDocumento", param.getNroDocumento());
		logueo.setParametro("nombres", param.getNombres());
		logueo.setParametro("apellidos", param.getApellidos());

		ResultInformarPagoRedes datos = new ResultInformarPagoRedes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarInformarPagoRedes(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().informarPagoRedes(param);
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

	public ResultExisteCliente existeCliente(ParamExisteCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("existeCliente");
		logueo.setParametro("Num Documento", param.getNumDocumento());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());

		ResultExisteCliente datos = new ResultExisteCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarExisteCliente(param);
			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().existeCliente(param);
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

	@Override
	public ResultObtenerMaximoEndoso obtenerMaximoEndoso(ParamObtenerMaximoEndoso param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerMaximoEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("codRamo", param.getCodRamo());
		logueo.setParametro("numPoliza", param.getNumPoliza());

		ResultObtenerMaximoEndoso datos = new ResultObtenerMaximoEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerMaximoEndoso(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerMaximoEndoso(param);
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

	@Override
	public ResultValidacionSOA validacionSOA(ParamValidacionSOA param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("validarSOA");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("codRamo", param.getCodRamo());
		logueo.setParametro("numPoliza", param.getNumPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("tipoDocumento", param.getTipoDocumento());
		logueo.setParametro("nroDocumento", param.getNroDocumento());
		logueo.setParametro("productor", param.getProductor());

		ResultValidacionSOA datos = new ResultValidacionSOA();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarValidacionSOA(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().validacionSOA(param);
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

	@Override
	public ResultValidacionCartaVerde validacionCartaVerde(ParamValidacionCartaVerde param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("validacionCartaVerde");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("codRamo", param.getCodRamo());
		logueo.setParametro("numPoliza", param.getNumPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("tipoDocumento", param.getTipoDocumento());
		logueo.setParametro("nroDocumento", param.getNroDocumento());
		logueo.setParametro("productor", param.getProductor());

		ResultValidacionCartaVerde datos = new ResultValidacionCartaVerde();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarValidacionCartaVerde(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().validacionCartaVerde(param);
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

	@Override
	public ResultLogActividadMibseWsExt logActividadMibseWsExt(ParamLogActividadMibseWsExt param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("logActividadMibseWsExt");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("evento", param.getEvento());
		logueo.setParametro("detalle", param.getDetalle());
		logueo.setParametro("usuario", param.getUsuario());

		ResultLogActividadMibseWsExt datos = new ResultLogActividadMibseWsExt();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarLogActividadMibseWsExt(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().logActividadMibseWsExt(param);
				if (datos.getCodError() != 0) {
					error = new ServiciosError();
					error.setCodigo(datos.getCodError());
					error.setDescripcion(datos.getDescError());
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
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

	public ResultGenerico borrarMailCliente(ParamBorrarMailCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("borrarMailCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		if (param.getComunicacion() != null) {
			logueo.setParametro("Codigo comunicacion", param.getComunicacion().getCodComunicacion());
			logueo.setParametro("Valor comunicacion", param.getComunicacion().getValorComunicacion());
		}
		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarBorrarMailCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().borrarMailCliente(param);
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

	public ResultGenerico modificarMailCliente(ParamModificarMailCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("modificarMailCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		if (param.getComunicacion() != null) {
			logueo.setParametro("Codigo comunicacion", param.getComunicacion().getCodComunicacion());
			logueo.setParametro("Tipo comunicacion", param.getComunicacion().getTipoComunicacion());
			logueo.setParametro("Validada", param.getComunicacion().getValidada());
			logueo.setParametro("Valor comunicacion", param.getComunicacion().getValorComunicacion());
		}
		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarModificarMailCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().modificarMailCliente(param);
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

	
	public ResultGenerico altaMailCliente(ParamAltaMailCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("altaMailCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		if (param.getComunicacion() != null) {
			logueo.setParametro("Tipo comunicacion", param.getComunicacion().getTipoComunicacion());
			logueo.setParametro("Validada", param.getComunicacion().getValidada());
			logueo.setParametro("Valor comunicacion", param.getComunicacion().getValorComunicacion());
		}
		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarAltaMailCliente(param);
			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().altaMailCliente(param);
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

	
	public ResultObtenerMailsEnvioFacturaCliente obtenerMailsEnvioFacturaCliente(
			ParamObtenerMailsEnvioFacturaCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerMailsEnvioFacturaCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultObtenerMailsEnvioFacturaCliente datos = new ResultObtenerMailsEnvioFacturaCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerMailsEnvioFacturaCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerMailsEnvioFacturaCliente(param);
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
	
	public ResultValidarCertificadoLibreDeudaADT validarCertificadoLibreDeudaADT(ParamValidarCertificadoLibreDeudaADT param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("validarCertificadoLibreDeudaADT");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultValidarCertificadoLibreDeudaADT datos = new ResultValidarCertificadoLibreDeudaADT();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarCertificadoLibreDeudaADT(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().validarCertificadoLibreDeudaADT(param);
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
	
	public ResultObtenerMapaMsgSiniestro obtenerMapaMsgSiniestro(ParamObtenerMapaMsgSiniestro param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerMapaMsgSiniestro");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultObtenerMapaMsgSiniestro datos = new ResultObtenerMapaMsgSiniestro();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarMapaMsgSiniestro(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().ObtenerMapaMsgSiniestro(param);
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

	public ResultSubirArchivo subirArchivo(ParamSubirArchivo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("subirArchivo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("nombreArchivo", param.getNombreArchivo());
		logueo.setParametro("archivo null? ", param.getArchivo() == null);

		ResultSubirArchivo result = new ResultSubirArchivo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarSubirArchivo(param);

			if (error == null) {
				result = WsServiciosMiBse.getEJBManager().subirArchivo(param);
			} else {
				result.setError(error);
				result.setHayError(Boolean.TRUE);
			}
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, result);
		}
		return result;
	}
	
	public ResultProCarta proCarta(ParamProCarta param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("validarCertificadoLibreDeudaADT");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		ResultProCarta datos = new ResultProCarta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarProCarta(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().proCarta(param);
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

	@Override
	public ResultCorrespondeCartaPoliza correspondeCartaPoliza(ParamCorrespondeCartaPoliza param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("correspondeCartaPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());


		logueo.setParametro("sucursal", param.getSucursal());
		logueo.setParametro("codRamo", param.getRamo());
		logueo.setParametro("numPoliza", param.getPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("cdCarta", param.getCdCarta());

		ResultCorrespondeCartaPoliza datos = new ResultCorrespondeCartaPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarCorrespondeCartaPoliza(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().correspondeCartaPoliza(param);
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

	@Override
	public ResultProCarta proCarta2(ParamProCarta2 param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("proCarta2");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("ramo", param.getRamo());
		logueo.setParametro("poliza", param.getPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("cdCarta", param.getCdCarta());
		
		ResultProCarta datos = new ResultProCarta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarProCarta2(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().proCarta2(param);
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

	public ResultObtenerPolizasFacturasPagasCliente obtenerPolizasFacturasPagasCliente(ParamObtenerPolizasFacturasPagasCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerPolizasFacturasPagasCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultObtenerPolizasFacturasPagasCliente datos = new ResultObtenerPolizasFacturasPagasCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarObtenerPolizasFacturasPagasCliente(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().obtenerPolizasFacturasPagasCliente(param);
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

	@Override
	public ResultAdherirFacturaDigital adherirFacturaDigital(ParamAdherirFacturaDigital param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("adherirFacturaDigital");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("sucursal", param.getSucursal());
		logueo.setParametro("codRamo", param.getCodRamo());
		logueo.setParametro("numPoliza", param.getNumPoliza());

		ResultAdherirFacturaDigital datos = new ResultAdherirFacturaDigital();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMiBse validar = new ValidacionesMiBse();
			ServiciosError error = validar.validarAdherirFacturaDigital(param);

			if (error == null) {
				datos = WsServiciosMiBse.getEJBManager().adherirFacturaDigital(param);
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
