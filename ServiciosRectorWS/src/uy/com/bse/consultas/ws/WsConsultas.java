package uy.com.bse.consultas.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.consultas.operaciones.ParamActualizarPolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ParamObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ParamObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ParamObtenerPolizasFlotantes;
import uy.com.bse.consultas.operaciones.ParamTipoIntegranteNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ResultObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ResultObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ResultObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ResultObtenerPolizasFlotantes;
import uy.com.bse.serviciosEJB.ConsultasLocal;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.consultas.ws.IWsConsultas", serviceName = "WsConsultas")
public class WsConsultas implements IWsConsultas {

	private static Logger LOG = LogManager.getLogger(WsConsultas.class);

	public ResultObtenerCabezalNomina obtenerCabezalNomina(ParamObtenerCabezalNomina param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerCabezalNomina");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num nomina", param.getNumNomina());
		logueo.setParametro("Num integrante", param.getNumIntegrante());

		ResultObtenerCabezalNomina datos = new ResultObtenerCabezalNomina();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerCabezalNomina(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerCabezalNomina(param);
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

	public ResultObtenerCoberturasIntegranteNomina obtenerCoberturasIntegranteNomina(ParamObtenerCoberturasIntegranteNomina param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerCoberturasIntegranteNomina");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num nomina", param.getNumNomina());
		logueo.setParametro("Num integrante", param.getNumIntegrante());

		ResultObtenerCoberturasIntegranteNomina datos = new ResultObtenerCoberturasIntegranteNomina();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerCoberturasIntegranteNomina(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerCoberturasIntegranteNomina(param);
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

	public ResultCodiguera listaTipoIntegranteNomina(ParamTipoIntegranteNomina param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("listaTipoIntegranteNomina");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarListaTipoIntegranteNomina(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().listaTipoIntegranteNomina(param);
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

	public ResultObtenerIntegrantesNomina obtenerIntegrantesNomina(ParamObtenerIntegrantesNomina param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerIntegrantesNomina");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num detalle", param.getNumDetalle());

		ResultObtenerIntegrantesNomina datos = new ResultObtenerIntegrantesNomina();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerIntegrantesNomina(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerIntegrantesNomina(param);
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
	
	public ResultObtenerNominasVida obtenerNominasVida(ParamObtenerNominasVida param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerNominasVida");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num nomina", param.getNumNomina());
		logueo.setParametro("Tipo integrante", param.getTipoIntegrante());
		logueo.setParametro("Num certificado", param.getNumCertificado());
		logueo.setParametro("Vigente", param.getVigente());
		logueo.setParametro("Col orden", param.getColumnaOrden());
		logueo.setParametro("Num documento", param.getNumDocumento());
		logueo.setParametro("Primer nombre", param.getPrimerNombre());
		logueo.setParametro("Primer apellido", param.getPrimerApellido());
		logueo.setParametro("Num documento relacionado", param.getNumDocumentoRelacionado());

		ResultObtenerNominasVida datos = new ResultObtenerNominasVida();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerNominasVida(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerNominasVida(param);
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
	
	
	public ResultObtenerPolizasFlotantes obtenerPolizasFlotantes(ParamObtenerPolizasFlotantes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerPolizasFlotantes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Nombre", param.getNombreAsegurado());
		logueo.setParametro("Num. poliza", param.getNumPoliza());
		logueo.setParametro("Identificador web", param.getIdentificadorWeb());
		logueo.setParametro("Fecha embarque", param.getFechaEmbarque());
		logueo.setParametro("Identificador embarque", param.getIdentificadorEmbarque());
		logueo.setParametro("Num. poliza factura", param.getNumPolizaFactura());
		logueo.setParametro("Certificado no facturado", param.getCertNoFac());

		ResultObtenerPolizasFlotantes datos = new ResultObtenerPolizasFlotantes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerPolizasFlotantes(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerPolizasFlotantes(param);
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

	public ResultObtenerDetallePolizaFlotante obtenerDetallePolizaFlotante(ParamObtenerDetallePolizaFlotante param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerDetallePolizaFlotante");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Identificador", param.getIdentificador());

		ResultObtenerDetallePolizaFlotante datos = new ResultObtenerDetallePolizaFlotante();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerDetallePolizaFlotante(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerDetallePolizaFlotante(param);
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
	
	
	public ResultGenerico actualizarPolizaFlotante(final ParamActualizarPolizaFlotante param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerCoberturasIntegranteNomina");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Identificador", param.getIdentificadorWeb());
		logueo.setParametro("Num DUA", param.getNumDua());
		logueo.setParametro("Observaciones", param.getObservaciones());

		ResultGenerico datos = new ResultGenerico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarActualizarPolizaFlotante(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().actualizarPolizaFlotante(param);
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
	

	public ResultObtenerMovimientosComision obtenerMovimientosComision(ParamObtenerMovimientosComision param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerMovimientosComision");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		

		ResultObtenerMovimientosComision datos = new ResultObtenerMovimientosComision();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerMovimientosComision(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerMovimientosComision(param);
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
	public ResultObtenerIntegrantesNominaAccidenteTrabajo obtenerIntegrantesNominaAccidenteTrabajo(ParamObtenerIntegrantesNominaAccidenteTrabajo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultas.class);
		logueo.setMetodo("obtenerIntegrantesNominaAccidenteTrabajo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cod ramo", param.getCodRamo());
		logueo.setParametro("Num poliza", param.getNumPoliza());
		logueo.setParametro("Mes cargo", param.getMesCargo());
		logueo.setParametro("tipo documento", param.getTipoDocumento());
		logueo.setParametro("Num documento", param.getNumDocumento());
		logueo.setParametro("nombre", param.getNombre());
		logueo.setParametro("Cod productor", param.getCodProductor());
		
		ResultObtenerIntegrantesNominaAccidenteTrabajo datos = new ResultObtenerIntegrantesNominaAccidenteTrabajo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultas validar = new ValidacionesConsultas();
			ServiciosError error = validar.validarObtenerIntegrantesNominaAccidenteTrabajo(param);

			if (error == null) {
				datos = WsConsultas.getEJBManager().obtenerIntegrantesNominaAccidenteTrabajo(param);
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

	public static ConsultasLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (ConsultasLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Consultas!uy.com.bse.serviciosEJB.ConsultasLocal");
	}

	
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO CONSULTAS Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}

	

	

	
}
