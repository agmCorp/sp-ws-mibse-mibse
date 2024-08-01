package uy.com.bse.guc.logica;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.interfaces.GUCValues;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.seguridad.PasswordGenerator;
import uy.com.bse.utilitario.util.MailBuilder;

public final class OlvidoClaveSolver extends AbstractSolver {

	private static final Logger logger = LogManager.getLogger(OlvidoClaveSolver.class);
	private static final String FILE = "/smtp.properties";

	private ResultGenerico nuevaClave(ParamOlvidoClave param) {
		ResultOlvidoClave result = null;
		if (param != null) {
			result = new ResultOlvidoClave();
			PasswordGenerator p = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useLower(true).useUpper(true).build();
			String nuevaClave = p.generate(12);
			ParamOlvidoClave paramReset = new ParamOlvidoClave();
			paramReset.setClave(param.getClave());
			paramReset.setUsuario(param.getUsuario());
			paramReset.setTipoClave(GUCValues.CLAVERESET);
			paramReset.setEmail(param.getEmail());

			paramReset.setClaveNueva(nuevaClave);
			result = (ResultOlvidoClave) checkNull(DataProviderUtil.getDataProvider().olvidoClave(paramReset));
			if (!result.getHayError().booleanValue()) {
				if (enviarMail(param.getEmail(), nuevaClave)) {
					result.setMensajeInformacion("Se envi\u00f3 con \u00e9xito una nueva contrase\u00f1a.");
				} else {
					result.setError(new ServiciosError(350,"No fue posible enviar su nueva contrase\u00f1a. Por favor comunicarse con el banco."));
					result.setHayError(Boolean.TRUE);
					result.setMensajeInformacion("No fue posible enviar su nueva contrase\u00f1a. Por favor comunicarse con el banco.");
				}
			}
		}
		return checkNull(result);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultOlvidoClave();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(nuevaClave((ParamOlvidoClave) param));
	}

	private boolean enviarMail(String mail, String clave) {

		boolean resultado = true;

		// FIXME OIGRES TERMINAR LO DEL EMVIO DE MAIL
		Properties properties = new Properties();
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configGUC.properties");
			properties.load(OlvidoClaveSolver.class.getResourceAsStream(FILE));

			String smtpHost = manager.obtenerValor("util.mail.smtpHost");
			String smtpPort = manager.obtenerValor("util.mail.smtpPort");
			boolean smtpAuth = Boolean.parseBoolean(manager.obtenerValor("util.mail.smtpAuth"));
			String userAuth = properties.getProperty("userAuth");
			String passwordAuth = properties.getProperty("passwordAuth");
			String socketFactoryClass = properties.getProperty("socketFactoryClass");
			String socketFactoryPort = properties.getProperty("socketFactoryPort");
			String from = manager.obtenerValor("util.mail.from"); 
			String to = mail;
			boolean contentHtml = Boolean.parseBoolean(properties.getProperty("contentHtml"));

			try {
				resultado = MailBuilder.create().withSmtpHost(smtpHost).withSmtpPort(smtpPort).withSmtpAuth(smtpAuth).withUserAuth(userAuth).withPasswordAuth(passwordAuth)
						.withSocketFactoryClass(socketFactoryClass).withSocketFactoryPort(socketFactoryPort).withFrom(from).withTo(to).withSubject("Notificaci\u00f3n de Reseteo de Contrase\u00f1a")
						.withContent(contentHtml).withBody("Se ha generado para su usuario la clave: " + clave).build().send();
			} catch (AddressException e) {
				logger.error("Error en m\u00e9todo enviarMail", e);
				resultado = false;
			} catch (MessagingException e) {
				logger.error("Error en m\u00e9todo enviarMail", e);
				resultado = false;
			}

		} catch (IOException e) {
			logger.error("Error en m\u00e9todo enviarMail", e);
			resultado = false;
		}
		return resultado;
	}

}
