package uy.com.bse.mibse.sp.ws.mibse.utilitario.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;

public class MailBuilder {
	static Logger log = LogManager.getLogger(MailBuilder.class);
	String smtpHost;
	String smtpPort;
	Boolean smtpAuth;
	String userAuth;
	String passwordAuth;
	String socketFactoryClass;
	String socketFactoryPort;
	String from;
	String personal;
	String to;
	String cc;
	String bcc;
	String subject;
	Boolean contentHtml;
	String body;
	String file;

	public void sentToDefaultConfigureMail(String mail, String ccMail, String mensajeBody, String mensajeTitle) {

		String smtpHost = obtenerValor("util.mail.smtpHost");
		String smtpPort = obtenerValor("util.mail.smtpPort");
		boolean smtpAuth = false;

		String from = obtenerValor("util.mail.from");
		String to = mail;
		String cc = ccMail;
		boolean contentHtml = false;

		try {
			try {
				MailBuilder.create().withSmtpHost(smtpHost).withSmtpPort(smtpPort).withSmtpAuth(smtpAuth).withFrom(from).withTo(to).withCc(cc).withSubject(mensajeTitle).withContent(contentHtml)
						.withBody(mensajeBody).build().send();
			} catch (IOException e) {
				log.error("Error en m\u00e9todo enviarMail", e);
			}
		} catch (AddressException e) {
			log.error("Error en m\u00e9todo enviarMail", e);

		} catch (MessagingException e) {
			log.error("Error en m\u00e9todo enviarMail", e);
		}
	}

	private String obtenerValor(String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Correo");
		logueo.setClase(MailBuilder.class);
		logueo.setMetodo("obtenerValor");

		String retorno = new String();

		InputStream input = null;
		String filename = "externalConfig.properties";
		Properties prop = new Properties();

		input = MailBuilder.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			logueo.setError("No se pudo encontrar el archivo: " + filename);
			log.error(logueo.getMensaje());
		} else {
			try {
				prop.load(input);
				retorno = prop.getProperty(clave);
			} catch (IOException e) {
				log.error(e.getMessage());
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}

		return retorno;
	}

	public static MailBuilder create() {
		return new MailBuilder();
	}

	public MailBuilder withSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
		return this;
	}

	public MailBuilder withSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
		return this;
	}

	public MailBuilder withSmtpAuth(Boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
		return this;
	}

	public MailBuilder withUserAuth(String userAuth) {
		this.userAuth = userAuth;
		return this;
	}

	public MailBuilder withPasswordAuth(String passwordAuth) {
		this.passwordAuth = passwordAuth;
		return this;
	}

	public MailBuilder withSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
		return this;
	}

	public MailBuilder withSocketFactoryPort(String socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
		return this;
	}

	public MailBuilder withFrom(String from) {
		this.from = from;
		return this;
	}

	public MailBuilder withPersonal(String personal) {
		this.personal = personal;
		return this;
	}

	public MailBuilder withTo(String to) {
		this.to = to;
		return this;
	}

	public MailBuilder withCc(String cc) {
		this.cc = cc;
		return this;
	}

	public MailBuilder withBcc(String bcc) {
		this.bcc = bcc;
		return this;
	}

	public MailBuilder withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public MailBuilder withContent(Boolean contentHtml) {
		this.contentHtml = contentHtml;
		return this;
	}

	public MailBuilder withBody(String body) {
		this.body = body;
		return this;
	}

	public MailBuilder withFile(String file) {
		this.file = file;
		return this;
	}

	public Mail build() {
		return new Mail(this);
	}
}
