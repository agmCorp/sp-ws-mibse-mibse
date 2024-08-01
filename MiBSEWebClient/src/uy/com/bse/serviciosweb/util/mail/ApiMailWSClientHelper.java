package uy.com.bse.serviciosweb.util.mail;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import apimailws.apimailwsclient.ApiMailWS;
import apimailws.apimailwsclient.ApiMailWSService;
import apimailws.apimailwsclient.EnviarMailRequest;
import apimailws.apimailwsclient.EnviarMailResponse;
import apimailws.apimailwsclient.GenericFault_Exception;
import apimailws.apimailwsclient.ValidationsFault;
import uy.com.bse.utilitario.properties.EARPropertiesManager;

public class ApiMailWSClientHelper {
	private static Logger logger = LogManager.getLogger(ApiMailWSClientHelper.class);

	private String mailUrl;
	private ApiMailWSBuilder builder;

	public ApiMailWSClientHelper(ApiMailWSBuilder builder) {
		EARPropertiesManager manager = new EARPropertiesManager("configMibse.properties");
		this.mailUrl = manager.obtenerValor("apimailws.mailUrl");
		this.builder = builder;
	}

	public String send() {
		String mailId = "";
		try {
			ApiMailWSService service = new ApiMailWSService(new URL(this.mailUrl + "?wsdl"),
					new QName("http://bse.com.uy/services/apimailWS/apimailWS", "ApiMailWSService"));
			ApiMailWS apiMailWS = service.getApiMailWSPort();

			EnviarMailRequest enviarMailRequest = new EnviarMailRequest();
			enviarMailRequest.setAdjuntos(builder.adjuntos);
			enviarMailRequest.setAsunto(builder.asunto);
			enviarMailRequest.setCuerpo(builder.cuerpo);
			enviarMailRequest.setDestinatario(builder.destinatario);
			enviarMailRequest.setLista(builder.lista);
			enviarMailRequest.setMetadatos(builder.metadatos);
			enviarMailRequest.setNombreRemitente(builder.nombreRemitente);
			enviarMailRequest.setRemitente(builder.remitente);
			
			EnviarMailResponse enviarMailResponse = apiMailWS.enviarMail(enviarMailRequest);
			mailId = enviarMailResponse != null ? enviarMailResponse.getIdMail() : "";
		} catch (MalformedURLException | GenericFault_Exception | ValidationsFault e) {
			logger.error("Error en el método enviarMail", e);
		}
		return mailId;
	}
}
