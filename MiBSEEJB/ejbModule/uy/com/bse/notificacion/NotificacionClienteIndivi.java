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
import uy.com.bse.mibse.ClientePagoIndivi;
import uy.com.bse.mibse.ParamValidacionCartaVerde;
import uy.com.bse.mibse.ResultValidacionCartaVerde;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.serviciosweb.util.mail.ApiMailWSBuilder;
import uy.com.bse.serviciosweb.util.report.RWClientHelper;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;

public class NotificacionClienteIndivi implements INotificacionCliente, Serializable {
	private static final long serialVersionUID = -7829339291428245573L;
	private static Logger logger = LogManager.getLogger(NotificacionClienteIndivi.class);
	
	private static final String PLANTILLA_DEBITO = "/plantillas/indiviDebito-exito.html";
	private static final String PLANTILLA_REDES = "/plantillas/indiviRedes-exito.html";
	private static final String REMITENTE = "noreply@bse.com.uy";
	private static final String NOMBRE_REMITENTE = "ComunicacionesBSE";
	private static final String ASUNTO_DEBITO = "INDIVI | Transacción realizada con éxito";
	private static final String ASUNTO_REDES = "INDIVI | Pago en redes de cobranza";
	private static final String NOM_AD_DUP = "duplicadoFactura.pdf";
	private static final String NOM_AD_POL = "poliza.pdf";
	private static final String NOM_AD_CER_SOA = "certificadoSOA.pdf";
	private static final String NOM_AD_CER_MER = "certificadoMercosur.pdf";
	private static final Integer ORDEN = 9621;
	private static final Integer SUB_ORDEN = 1;
	
	private ClientePagoIndivi clientePagoIndivi;

	public NotificacionClienteIndivi(ClientePagoIndivi clientePagoIndivi) {
		this.clientePagoIndivi = clientePagoIndivi;
	}

	@Override
	public ResultGenerico notificar() {
		ResultGenerico result;
		if (clientePagoIndivi.getCodTipoMedioPago() == ClientePago.EnumTipoMedioDePago.DEBITO) {
			result = notificarDebito();
		} else {
			result = notificarRedes();
		}
		return result;
	}

	private ResultGenerico notificarRedes() {
		logger.debug("Inicio notificacionClienteIndivi notificarRedes");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		mailId = enviarMailRedes();
		if (mailId == null || mailId.equals("")) {
			setGenericError(result, "Error en método notificar() al enviar mail");				
		} else {
			logger.info("Se ha notificado " + clientePagoIndivi.getIdTransaccion() + 
					" al cliente " + clientePagoIndivi.getTipoDocumento() + " " + clientePagoIndivi.getNroDocumento() + 
					" vía mail " + clientePagoIndivi.getMail() + " con mailId " + mailId);
		}
		logger.debug("Fin notificacionClienteIndivi notificarRedes");
		
		return result;
	}
	
	private ResultGenerico notificarDebito() {
		logger.debug("Inicio notificacionClienteIndivi notificarDebito");
		
		ResultGenerico result = new ResultGenerico();
		String mailId = "";

		// Generación de reportes
		byte[] duplicadoFactura = reporteDuplicadoFactura();
		byte[] poliza = reportePoliza();
		byte[] certificadoSOA = reporteCertificadoSOA();
		byte[] certificadoMercosur = reporteCertificadoMercosur();

		if (duplicadoFactura != null && duplicadoFactura.length > 0 && 
				poliza != null && poliza.length > 0 &&
				certificadoSOA != null && certificadoSOA.length > 0 &&
				certificadoMercosur != null && certificadoMercosur.length > 0) {
			mailId = enviarMailDebito(duplicadoFactura, poliza, certificadoSOA, certificadoMercosur);
			if (mailId == null || mailId.equals("")) {
				setGenericError(result, "Error en método notificar() al enviar mail");				
			} else {
				logger.info("Se ha notificado el pago " + clientePagoIndivi.getIdTransaccion() + 
						" al cliente " + clientePagoIndivi.getTipoDocumento() + " " + clientePagoIndivi.getNroDocumento() + 
						" vía mail " + clientePagoIndivi.getMail() + " con mailId " + mailId);
			}
		} else {
			setGenericError(result, "Error en método notificar() al crear reportes");
		}
		logger.debug("Fin notificacionClienteIndivi notificarDebito");

		return result;
	}
	
	private void setGenericError(ResultGenerico result, String descripcion) {
		result.setHayError(true);
		ServiciosError error = new ServiciosError();
		error.setCodigo(Values.CODDEFECTO);
		error.setDescripcion(descripcion);
		result.setError(error);
	}
	
	private String enviarMailDebito(byte[] duplicadoFactura, byte[] poliza, byte[] certificadoSOA, byte[] certificadoMercosur) {
		String mailId = "";
		InputStream input = NotificacionClienteIndivi.class.getResourceAsStream(PLANTILLA_DEBITO);
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

				// Certificado SOA
				adjunto = new Adjunto();
				adjunto.setArchivo(certificadoSOA);
				adjunto.setNombre(NOM_AD_CER_SOA);
				adjuntos.getAdjunto().add(adjunto);

				// Certificado Mercosur
				adjunto = new Adjunto();
				adjunto.setArchivo(certificadoMercosur);
				adjunto.setNombre(NOM_AD_CER_MER);
				adjuntos.getAdjunto().add(adjunto);

				mailId = ApiMailWSBuilder.create().withRemitente(REMITENTE).withNombreRemitente(NOMBRE_REMITENTE)
						.withDestinatario(clientePagoIndivi.getMail()).withAsunto(ASUNTO_DEBITO)
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
		InputStream input = NotificacionClienteIndivi.class.getResourceAsStream(PLANTILLA_REDES);
		if (input != null) {
			String contenidoHTML = toString(input, StandardCharsets.UTF_8);
			if (contenidoHTML != null && !contenidoHTML.trim().equals("")) {
				contenidoHTML = personalizarRedes(contenidoHTML);

				mailId = ApiMailWSBuilder.create().withRemitente(REMITENTE).withNombreRemitente(NOMBRE_REMITENTE)
						.withDestinatario(clientePagoIndivi.getMail()).withAsunto(ASUNTO_REDES)
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
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoIndivi.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<ID_VEHICULO>", clientePagoIndivi.getMotor().equals("") ? clientePagoIndivi.getChasis() : clientePagoIndivi.getMotor());
		return contenidoHTML;
	}

	private String personalizarRedes(String contenidoHTML) {
		contenidoHTML = contenidoHTML.replaceAll("<NOMBRE_COMPLETO>", clientePagoIndivi.getNombreCompleto());
		contenidoHTML = contenidoHTML.replaceAll("<ID_VEHICULO>", clientePagoIndivi.getMotor().equals("") ? clientePagoIndivi.getChasis() : clientePagoIndivi.getMotor());
		contenidoHTML = contenidoHTML.replaceAll("<DOCUMENT_ID>", clientePagoIndivi.getDocumentId());
		return contenidoHTML;
	}
	
	private byte[] reporteDuplicadoFactura() {
		return RWClientHelper.getInstance().imprimir("CFER60001", "DOCUMENT_ID=" + clientePagoIndivi.getDocumentId());
	}
	
	private byte[] reportePoliza() {
		Long nroSecuenciaPoliza = clientePagoIndivi.getNroSecuenciaPoliza();
		ServiciosMiBsePersist serviciosMiBsePersis = new ServiciosMiBsePersist();
		ResultGenerico result = serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SUCURSAL", clientePagoIndivi.getSucursal().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "RAMO", clientePagoIndivi.getCodRamo().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "POLIZA", clientePagoIndivi.getNumPoliza().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ENDOSO", clientePagoIndivi.getNroEndoso().toString(), ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "TIPO", "I", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "SEC_POLIZA_LOTE", "1", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ITEMS", "N 01 02 03", ORDEN, SUB_ORDEN);
		result = result.getHayError() ? result : serviciosMiBsePersis.gentOrdenes(nroSecuenciaPoliza, "ORIGEN", "I", ORDEN, SUB_ORDEN);
		return result.getHayError() ? null : RWClientHelper.getInstance().imprimir("CARR10800", "NU_SEC=" + clientePagoIndivi.getNroSecuenciaPoliza()); 
	}

	private byte[] reporteCertificadoSOA() {
		return RWClientHelper.getInstance().imprimir("CARR10808", "P_ramo=" + clientePagoIndivi.getCodRamo() + " P_poliza="
				+ clientePagoIndivi.getNumPoliza() + " P_certificado=" + clientePagoIndivi.getCertificado());
	}

	private byte[] reporteCertificadoMercosur() {
		byte[] certificadoMercosur = null;
		
		ParamValidacionCartaVerde param = new ParamValidacionCartaVerde();
		param.setCodRamo(clientePagoIndivi.getCodRamo());
		param.setNumPoliza(clientePagoIndivi.getNumPoliza());
		param.setCertificado(clientePagoIndivi.getCertificado());
		param.setTipoDocumento(clientePagoIndivi.getTipoDocumento());
		param.setNroDocumento(clientePagoIndivi.getNroDocumento());
		param.setProductor(null);
		ServiciosMiBsePersist serviciosMiBsePersis = new ServiciosMiBsePersist();
		ResultValidacionCartaVerde result = serviciosMiBsePersis.validacionCartaVerde(param);
		if (result.getHayError()) {
			logger.error(result.getError().getDescripcion());
		} else {
			certificadoMercosur = RWClientHelper.getInstance().imprimir("CARR10801", "P_RAMO=" + param.getCodRamo() + " P_POLIZA=" + param.getNumPoliza() + 
				       " P_CERTIFICADO=" + param.getCertificado() + " P_SUCURSAL=" + result.getSucursal() + 
				       " P_ENDOSO=" + result.getEndoso() + " P_FORMULARIO=" + result.getFormulario() +
				       " P_CONSECUTIVO_BIEN=" + result.getConsecutivo());
		}
		
		return certificadoMercosur;
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
