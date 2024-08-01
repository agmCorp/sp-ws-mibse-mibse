package uy.com.bse.notificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import apimailws.apimailwsclient.Adjunto;
import apimailws.apimailwsclient.EnviarMailRequest.Adjuntos;
import uy.com.bse.mibse.ClientePago;
import uy.com.bse.mibse.ClientePagoBici;
import uy.com.bse.mibse.ClientePagoOPersonal;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.serviciosweb.util.mail.ApiMailWSBuilder;
import uy.com.bse.serviciosweb.util.report.RWClientHelper;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;

public class NotificacionClienteOPersonal implements INotificacionCliente, Serializable {
	private static final long serialVersionUID = 4775216283086214289L;

	private static Logger logger = LogManager.getLogger(NotificacionClienteOPersonal.class);
	
	private static final String PLANTILLA_DEBITO = "/plantillas/opersonalDebito-exito.html";
	private static final String PLANTILLA_REDES = "/plantillas/opersonalRedes-exito.html";
	private static final String REMITENTE = "noreply@bse.com.uy";
	private static final String NOMBRE_REMITENTE = "ComunicacionesBSE";
	private static final String ASUNTO_DEBITO = "OBJETO PERSONALES | Transacción realizada con éxito";
	private static final String ASUNTO_REDES = "OBJETO PERSONALES | Pago en redes de cobranza";
	private static final String NOM_AD_DUP = "duplicadoFactura.pdf";
	private static final String NOM_AD_POL = "poliza.pdf";
	private static final Integer ORDEN = 9621;
	private static final Integer SUB_ORDEN = 1;
	
	private ClientePagoOPersonal clientePagoOPersonal;
	
	public NotificacionClienteOPersonal(ClientePagoOPersonal clientePagoOPersonal) {
		this.clientePagoOPersonal = clientePagoOPersonal;
	}

	@Override
	public ResultGenerico notificar() {
		ResultGenerico result;
		if (clientePagoOPersonal.getCodTipoMedioPago() == ClientePago.EnumTipoMedioDePago.DEBITO) {
			result = notificarDebito();
		} else {
			result = notificarRedes();
		}
		return result;
	}

	private ResultGenerico notificarRedes() {
		logger.debug("Inicio NotificacionClienteOPersonal notificarRedes");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		mailId = enviarMailRedes();
		if (mailId == null || mailId.equals("")) {
			setGenericError(result, "Error en método notificar() al enviar mail");				
		} else {
			logger.info("Se ha notificado " + clientePagoOPersonal.getIdTransaccion() + 
					" al cliente " + clientePagoOPersonal.getTipoDocumento() + " " + clientePagoOPersonal.getNroDocumento() + 
					" vía mail " + clientePagoOPersonal.getMail() + " con mailId " + mailId);
		}
		logger.debug("Fin NotificacionClienteOPersonal notificarRedes");
		
		return result;
	}

	private ResultGenerico notificarDebito() {
		logger.debug("Inicio NotificacionClienteOPersonal notificarDebito");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		// Generación de reportes
		byte[] duplicadoFactura = reporteDuplicadoFactura();
		byte[] poliza = reportePoliza();

		if (duplicadoFactura != null && duplicadoFactura.length > 0 && 
			poliza != null && poliza.length > 0) {
			
			mailId = enviarMailDebito(duplicadoFactura, poliza);
			if (mailId == null || mailId.equals("")) {
				setGenericError(result, "Error en método notificar() al enviar mail");				
			} else {
				logger.info("Se ha notificado el pago " + clientePagoOPersonal.getIdTransaccion() + 
						" al cliente " + clientePagoOPersonal.getTipoDocumento() + " " + clientePagoOPersonal.getNroDocumento() + 
						" vía mail " + clientePagoOPersonal.getMail() + " con mailId " + mailId);
			}
		} else {
			setGenericError(result, "Error en método notificar() al crear reportes");
		}
		logger.debug("Fin NotificacionClienteOPersonal notificarDebito");

		return result;
	}	
	
	private byte[] reporteDuplicadoFactura() {
		return RWClientHelper.getInstance().imprimir("CFER60001", "DOCUMENT_ID=" + clientePagoOPersonal.getDocumentId());
	}

	private byte[] reportePoliza() {
		Long nroSecuenciaPoliza = clientePagoOPersonal.getNroSecuenciaPoliza();
		ServiciosMiBsePersist serviciosMiBsePersis = new ServiciosMiBsePersist();
		ResultGenerico result = serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SUCURSAL", clientePagoOPersonal.getSucursal().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "RAMO", clientePagoOPersonal.getCodRamo().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "POLIZA", clientePagoOPersonal.getNumPoliza().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ENDOSO", clientePagoOPersonal.getNroEndoso().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "TIPO", "I", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SEC_POLIZA_LOTE", "1", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ITEMS", "N 01 02 03", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ORIGEN", "I", ORDEN, SUB_ORDEN);
		return result.getHayError() ? null : RWClientHelper.getInstance().imprimir("CARR10800", "NU_SEC=" + clientePagoOPersonal.getNroSecuenciaPoliza()); 
	}
	
	private void setGenericError(ResultGenerico result, String descripcion) {
		result.setHayError(true);
		ServiciosError error = new ServiciosError();
		error.setCodigo(Values.CODDEFECTO);
		error.setDescripcion(descripcion);
		result.setError(error);
	}
	
	private String enviarMailDebito(byte[] duplicadoFactura, byte[] poliza) {
		String mailId = "";
		InputStream input = NotificacionClienteOPersonal.class.getResourceAsStream(PLANTILLA_DEBITO);
		if (input != null) {
			String contenidoHTML = toString(input, StandardCharsets.UTF_8);
			if (contenidoHTML != null && !contenidoHTML.trim().equals("")) {
				contenidoHTML = personalizarDebito(contenidoHTML);

				Adjuntos adjuntos = new Adjuntos();
				
				// Duplicado de factura
				Adjunto adjunto = new Adjunto();
				adjunto.setArchivo(duplicadoFactura);
				adjunto.setNombre(NOM_AD_DUP);
				adjuntos.getAdjunto().add(adjunto);
				
				// Póliza
				adjunto = new Adjunto();
				adjunto.setArchivo(poliza);
				adjunto.setNombre(NOM_AD_POL);
				adjuntos.getAdjunto().add(adjunto);

				mailId = ApiMailWSBuilder.create().withRemitente(REMITENTE).withNombreRemitente(NOMBRE_REMITENTE)
						.withDestinatario(clientePagoOPersonal.getMail()).withAsunto(ASUNTO_DEBITO)
						.withCuerpo(contenidoHTML).withAdjuntos(adjuntos).build().send();
			} else {
				logger.error("No se pudo leer la plantilla " + PLANTILLA_DEBITO);
			}
		} else {
			logger.error("No se pudo encontrar el archivo: " + PLANTILLA_DEBITO);
		}
		return mailId;
	}

	private String enviarMailRedes() {
		String mailId = "";
		InputStream input = NotificacionClienteSOA.class.getResourceAsStream(PLANTILLA_REDES);
		if (input != null) {
			String contenidoHTML = toString(input, StandardCharsets.UTF_8);
			if (contenidoHTML != null && !contenidoHTML.trim().equals("")) {
				contenidoHTML = personalizarRedes(contenidoHTML);

				mailId = ApiMailWSBuilder.create().withRemitente(REMITENTE).withNombreRemitente(NOMBRE_REMITENTE)
						.withDestinatario(clientePagoOPersonal.getMail()).withAsunto(ASUNTO_REDES)
						.withCuerpo(contenidoHTML).build().send();
			} else {
				logger.error("No se pudo leer la plantilla " + PLANTILLA_REDES);
			}
		} else {
			logger.error("No se pudo encontrar el archivo: " + PLANTILLA_REDES);
		}
		return mailId;
	}
	
	private String toString(InputStream inputStream, Charset charset) {
		String str = ""; 
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
			str = br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException  e) {
			logger.error("Error en convert");
		}
		return str;
	}
	
	private String personalizarDebito(String contenidoHTML) {
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoOPersonal.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<ID_OPERSONAL>", clientePagoOPersonal.getSerie());
		return contenidoHTML;
	}
	
	private String personalizarRedes(String contenidoHTML) {
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoOPersonal.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<ID_OPERSONAL>", clientePagoOPersonal.getSerie());
		contenidoHTML = contenidoHTML.replaceAll("<DOCUMENT_ID>", clientePagoOPersonal.getDocumentId());		
		return contenidoHTML;
	}
}
