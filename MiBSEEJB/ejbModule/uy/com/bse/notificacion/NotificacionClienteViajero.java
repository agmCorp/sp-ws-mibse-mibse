package uy.com.bse.notificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import apimailws.apimailwsclient.Adjunto;
import apimailws.apimailwsclient.EnviarMailRequest.Adjuntos;
import uy.com.bse.mibse.ClientePago;
import uy.com.bse.mibse.ClientePagoViajero;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.serviciosweb.util.mail.ApiMailWSBuilder;
import uy.com.bse.serviciosweb.util.report.RWClientHelper;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;

public class NotificacionClienteViajero implements INotificacionCliente, Serializable {
	private static final long serialVersionUID = 7100823970757542118L;
	private static Logger logger = LogManager.getLogger(NotificacionClienteViajero.class);

	private static final String PLANTILLA_DEBITO = "/plantillas/viajerosDebito-exito.html";
	private static final String PLANTILLA_REDES = "/plantillas/viajerosRedes-exito.html";
	private static final String REMITENTE = "noreply@bse.com.uy";
	private static final String NOMBRE_REMITENTE = "ComunicacionesBSE";
	private static final String ASUNTO_DEBITO = "VIAJEROS | Transacción realizada con éxito";
	private static final String ASUNTO_REDES = "SOA | Pago en redes de cobranza";
	private static final String URL_INSTRUCTIVO = "https://www.bse.com.uy/wps/wcm/connect/14090175-2427-4f1d-be23-87e6ddb765f8/" + 
												  "Instructivo+para+utilizar+los+serivios+de+asist+card2.pdf?MOD=AJPERES&amp;" + 
												  "CACHEID=ROOTWORKSPACE-14090175-2427-4f1d-be23-87e6ddb765f8-lOIrEZG";
	private static final String URL_ASSISTCARD = "https://www.bse.com.uy/wps/wcm/connect/eba6f7ca-29c0-41fe-829f-67527349abea/" + 
												 "LISTADO+Centros+de+Asistencia+ASSIST+CARD+2018.pdf?MOD=AJPERES&amp;" + 
												 "CACHEID=ROOTWORKSPACE-eba6f7ca-29c0-41fe-829f-67527349abea-mjtkznR";
	private static final String NOM_AD_INS = "instructivo.pdf";
	private static final String NOM_AD_ACARD = "assistcard.pdf";
	private static final String NOM_AD_DUP = "duplicadoFactura.pdf";
	private static final String NOM_AD_POL = "poliza.pdf";
	private static final Integer ORDEN = 9621;
	private static final Integer SUB_ORDEN = 1;
	
	private ClientePagoViajero clientePagoViajero;
	
	public NotificacionClienteViajero(ClientePagoViajero clientePagoViajero) {
		this.clientePagoViajero = clientePagoViajero;
	}
	
	@Override
	public ResultGenerico notificar() {
		ResultGenerico result;
		if (clientePagoViajero.getCodTipoMedioPago() == ClientePago.EnumTipoMedioDePago.DEBITO) {
			result = notificarDebito();
		} else {
			result = notificarRedes();
		}
		return result;
	}

	private ResultGenerico notificarRedes() {
		logger.debug("Inicio notificacionClienteViajero notificarRedes");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		mailId = enviarMailRedes();
		if (mailId == null || mailId.equals("")) {
			setGenericError(result, "Error en método notificar() al enviar mail");				
		} else {
			logger.info("Se ha notificado " + clientePagoViajero.getIdTransaccion() + 
					" al cliente " + clientePagoViajero.getTipoDocumento() + " " + clientePagoViajero.getNroDocumento() + 
					" vía mail " + clientePagoViajero.getMail() + " con mailId " + mailId);
		}
		logger.debug("Fin notificacionClienteViajero notificarRedes");
		
		return result;
	}

	private ResultGenerico notificarDebito() {
		logger.debug("Inicio notificacionClienteViajero notificarDebito");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		// Generación de reportes
		try {
			byte[] instructivo = RWClientHelper.getInstance().getContent(new URL(URL_INSTRUCTIVO));
			byte[] assistcard = RWClientHelper.getInstance().getContent(new URL(URL_ASSISTCARD));
			byte[] duplicadoFactura = reporteDuplicadoFactura();
			byte[] poliza = reportePoliza();
	
			if (instructivo != null && instructivo.length > 0 &&
				assistcard != null && assistcard.length > 0 &&
				duplicadoFactura != null && duplicadoFactura.length > 0 && 
				poliza != null && poliza.length > 0) {
				
				mailId = enviarMailDebito(instructivo, assistcard, duplicadoFactura, poliza);
				if (mailId == null || mailId.equals("")) {
					setGenericError(result, "Error en método notificar() al enviar mail");				
				} else {
					logger.info("Se ha notificado el pago " + clientePagoViajero.getIdTransaccion() + 
							" al cliente " + clientePagoViajero.getTipoDocumento() + " " + clientePagoViajero.getNroDocumento() + 
							" vía mail " + clientePagoViajero.getMail() + " con mailId " + mailId);
				}
			} else {
				setGenericError(result, "Error en método notificar() al crear reportes");
			}
		} catch(Exception e) {
			logger.error(e);
			setGenericError(result, "Error en método notificar() al crear reportes");
		}
		logger.debug("Fin notificacionClienteViajero notificarDebito");

		return result;
	}	
	
	private byte[] reporteDuplicadoFactura() {
		return RWClientHelper.getInstance().imprimir("CFER60001", "DOCUMENT_ID=" + clientePagoViajero.getDocumentId());
	}

	private byte[] reportePoliza() {
		Long nroSecuenciaPoliza = clientePagoViajero.getNroSecuenciaPoliza();
		ServiciosMiBsePersist serviciosMiBsePersis = new ServiciosMiBsePersist();
		ResultGenerico result = serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SUCURSAL", clientePagoViajero.getSucursal().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "RAMO", clientePagoViajero.getCodRamo().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "POLIZA", clientePagoViajero.getNumPoliza().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ENDOSO", clientePagoViajero.getNroEndoso().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "TIPO", "I", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SEC_POLIZA_LOTE", "1", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ITEMS", "N 01 02 03", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ORIGEN", "I", ORDEN, SUB_ORDEN);
		return result.getHayError() ? null : RWClientHelper.getInstance().imprimir("CARR10800", "NU_SEC=" + clientePagoViajero.getNroSecuenciaPoliza()); 
	}
	
	private void setGenericError(ResultGenerico result, String descripcion) {
		result.setHayError(true);
		ServiciosError error = new ServiciosError();
		error.setCodigo(Values.CODDEFECTO);
		error.setDescripcion(descripcion);
		result.setError(error);
	}
	
	private String enviarMailDebito(byte[] instructivo, byte[] assistcard, byte[] duplicadoFactura, byte[] poliza) {
		String mailId = "";
		InputStream input = NotificacionClienteViajero.class.getResourceAsStream(PLANTILLA_DEBITO);
		if (input != null) {
			String contenidoHTML = toString(input, StandardCharsets.UTF_8);
			if (contenidoHTML != null && !contenidoHTML.trim().equals("")) {
				contenidoHTML = personalizarDebito(contenidoHTML);

				Adjuntos adjuntos = new Adjuntos();
				
				// Instructivo para utilizar los servicios de Assistcard
				Adjunto adjunto = new Adjunto();
				adjunto.setArchivo(instructivo);
				adjunto.setNombre(NOM_AD_INS);
				adjuntos.getAdjunto().add(adjunto);
				
				// Centros de asistencia Assistcard
				adjunto = new Adjunto();
				adjunto.setArchivo(assistcard);
				adjunto.setNombre(NOM_AD_ACARD);
				adjuntos.getAdjunto().add(adjunto);
				
				// Duplicado de factura
				adjunto = new Adjunto();
				adjunto.setArchivo(duplicadoFactura);
				adjunto.setNombre(NOM_AD_DUP);
				adjuntos.getAdjunto().add(adjunto);
				
				// Póliza
				adjunto = new Adjunto();
				adjunto.setArchivo(poliza);
				adjunto.setNombre(NOM_AD_POL);
				adjuntos.getAdjunto().add(adjunto);

				mailId = ApiMailWSBuilder.create().withRemitente(REMITENTE).withNombreRemitente(NOMBRE_REMITENTE)
						.withDestinatario(clientePagoViajero.getMail()).withAsunto(ASUNTO_DEBITO)
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
						.withDestinatario(clientePagoViajero.getMail()).withAsunto(ASUNTO_REDES)
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
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoViajero.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<NRO_POLIZA>", clientePagoViajero.getNumPoliza().toString());
		return contenidoHTML;
	}

	private String personalizarRedes(String contenidoHTML) {
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoViajero.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<NRO_POLIZA>", clientePagoViajero.getNumPoliza().toString());
		contenidoHTML = contenidoHTML.replaceAll("<DOCUMENT_ID>", clientePagoViajero.getDocumentId());		
		return contenidoHTML;
	}
}
