package uy.com.bse.notificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import apimailws.apimailwsclient.Adjunto;
import apimailws.apimailwsclient.EnviarMailRequest.Adjuntos;
import uy.com.bse.mibse.ClientePago;
import uy.com.bse.mibse.ClientePagoSOA;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.serviciosweb.util.mail.ApiMailWSBuilder;
import uy.com.bse.serviciosweb.util.report.RWClientHelper;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;

public class NotificacionClienteSOA implements INotificacionCliente, Serializable {
	private static final long serialVersionUID = -7144203163367093122L;
	private static Logger logger = LogManager.getLogger(NotificacionClienteSOA.class);
	
	private static final String PLANTILLA_DEBITO = "/plantillas/soaDebito-exito.html";
	private static final String PLANTILLA_REDES = "/plantillas/soaRedes-exito.html";
	private static final String REMITENTE = "noreply@bse.com.uy";
	private static final String NOMBRE_REMITENTE = "ComunicacionesBSE";
	private static final String ASUNTO_DEBITO = "SOA | Transacción realizada con éxito";
	private static final String ASUNTO_REDES = "SOA | Pago en redes de cobranza";
	private static final String NOM_AD_CER = "certificadoSOA.pdf";
	private static final String NOM_AD_DUP = "duplicadoFactura.pdf";
	private static final String NOM_AD_POL = "poliza.pdf";
	private static final Integer ORDEN = 9621;
	private static final Integer SUB_ORDEN = 1;
	
	private ClientePagoSOA clientePagoSOA;

	public NotificacionClienteSOA(ClientePagoSOA clientePagoSOA) {
		this.clientePagoSOA = clientePagoSOA;
	}

	@Override
	public ResultGenerico notificar() {
		ResultGenerico result;
		if (clientePagoSOA.getCodTipoMedioPago() == ClientePago.EnumTipoMedioDePago.DEBITO) {
			result = notificarDebito();
		} else {
			result = notificarRedes();
		}
		return result;
	}

	private ResultGenerico notificarRedes() {
		logger.debug("Inicio notificacionClienteSOA notificarRedes");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		mailId = enviarMailRedes();
		if (mailId == null || mailId.equals("")) {
			setGenericError(result, "Error en método notificar() al enviar mail");				
		} else {
			logger.info("Se ha notificado " + clientePagoSOA.getIdTransaccion() + 
					" al cliente " + clientePagoSOA.getTipoDocumento() + " " + clientePagoSOA.getNroDocumento() + 
					" vía mail " + clientePagoSOA.getMail() + " con mailId " + mailId);
		}
		logger.debug("Fin notificacionClienteSOA notificarRedes");
		
		return result;
	}
	
	private ResultGenerico notificarDebito() {
		logger.debug("Inicio notificacionClienteSOA notificarDebito");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		// Generación de reportes
		byte[] certificadoSOA = reporteCertificadoSOA();
		byte[] duplicadoFactura = reporteDuplicadoFactura();
		byte[] poliza = reportePoliza();

		if (certificadoSOA != null && certificadoSOA.length > 0 && 
				duplicadoFactura != null && duplicadoFactura.length > 0 && 
				poliza != null && poliza.length > 0) {
			mailId = enviarMailDebito(certificadoSOA, duplicadoFactura, poliza);
			if (mailId == null || mailId.equals("")) {
				setGenericError(result, "Error en método notificar() al enviar mail");				
			} else {
				logger.info("Se ha notificado el pago " + clientePagoSOA.getIdTransaccion() + 
						" al cliente " + clientePagoSOA.getTipoDocumento() + " " + clientePagoSOA.getNroDocumento() + 
						" vía mail " + clientePagoSOA.getMail() + " con mailId " + mailId);
			}
		} else {
			setGenericError(result, "Error en método notificar() al crear reportes");
		}
		logger.debug("Fin notificacionClienteSOA notificarDebito");

		return result;
	}
	
	private void setGenericError(ResultGenerico result, String descripcion) {
		result.setHayError(true);
		ServiciosError error = new ServiciosError();
		error.setCodigo(Values.CODDEFECTO);
		error.setDescripcion(descripcion);
		result.setError(error);
	}
	
	private String enviarMailDebito(byte[] certificadoSOA, byte[] duplicadoFactura, byte[] poliza) {
		String mailId = "";
		InputStream input = NotificacionClienteSOA.class.getResourceAsStream(PLANTILLA_DEBITO);
		if (input != null) {
			String contenidoHTML = toString(input, StandardCharsets.UTF_8);
			if (contenidoHTML != null && !contenidoHTML.trim().equals("")) {
				contenidoHTML = personalizarDebito(contenidoHTML);

				Adjuntos adjuntos = new Adjuntos();
				
				// Certificado SOA
				Adjunto adjunto = new Adjunto();
				adjunto.setArchivo(certificadoSOA);
				adjunto.setNombre(NOM_AD_CER);
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
						.withDestinatario(clientePagoSOA.getMail()).withAsunto(ASUNTO_DEBITO)
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
						.withDestinatario(clientePagoSOA.getMail()).withAsunto(ASUNTO_REDES)
						.withCuerpo(contenidoHTML).build().send();
			} else {
				logger.error("No se pudo leer la plantilla " + PLANTILLA_REDES);
			}
		} else {
			logger.error("No se pudo encontrar el archivo: " + PLANTILLA_REDES);
		}
		return mailId;
	}
	
	private String personalizarDebito(String contenidoHTML) {
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoSOA.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<ID_VEHICULO>", clientePagoSOA.getMatricula().equals("") ? clientePagoSOA.getChasis() : clientePagoSOA.getMatricula());
		return contenidoHTML;
	}

	private String personalizarRedes(String contenidoHTML) {
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoSOA.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<ID_VEHICULO>", clientePagoSOA.getMatricula().equals("") ? clientePagoSOA.getChasis() : clientePagoSOA.getMatricula());
		contenidoHTML = contenidoHTML.replaceAll("<DOCUMENT_ID>", clientePagoSOA.getDocumentId());
		return contenidoHTML;
	}
	
	private byte[] reporteCertificadoSOA() {
		return RWClientHelper.getInstance().imprimir("CARR10808", "P_ramo=" + clientePagoSOA.getCodRamo() + " P_poliza="
				+ clientePagoSOA.getNumPoliza() + " P_certificado=" + clientePagoSOA.getCertificado());
	}
	
	private byte[] reporteDuplicadoFactura() {
		return RWClientHelper.getInstance().imprimir("CFER60001", "DOCUMENT_ID=" + clientePagoSOA.getDocumentId());
	}
	
	private byte[] reportePoliza() {
		Long nroSecuenciaPoliza = clientePagoSOA.getNroSecuenciaPoliza();
		ServiciosMiBsePersist serviciosMiBsePersis = new ServiciosMiBsePersist();
		ResultGenerico result = serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SUCURSAL", clientePagoSOA.getSucursal().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "RAMO", clientePagoSOA.getCodRamo().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "POLIZA", clientePagoSOA.getNumPoliza().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ENDOSO", clientePagoSOA.getNroEndoso().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "TIPO", "I", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SEC_POLIZA_LOTE", "1", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ITEMS", "N 01 02 03", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ORIGEN", "I", ORDEN, SUB_ORDEN);
		return result.getHayError() ? null : RWClientHelper.getInstance().imprimir("CARR10800", "NU_SEC=" + clientePagoSOA.getNroSecuenciaPoliza()); 
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
	
	@SuppressWarnings("unused")
	private void guardarArchivoFilesystem(byte[] bytes, String rutaArchivo) {
		try {
			Path path = Paths.get(rutaArchivo);
		    Files.write(path, bytes);
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
