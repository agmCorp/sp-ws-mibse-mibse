package uy.com.bse.utilitario.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	private Properties mailServerProperties;
	private Session getMailSession;
	private MimeMessage generateMailMessage;

	private String smtpHost;
	private String smtpPort;
	private Boolean smtpAuth;
	private String userAuth;
	private String passwordAuth;
	private String socketFactoryClass;
	private String socketFactoryPort;

	private String from;
	private String personal;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private Boolean contentHtml;
	private String body;
	private String file;

	public Mail(MailBuilder builder) {
		this.smtpHost = builder.smtpHost;
		this.smtpPort = builder.smtpPort;
		this.smtpAuth = builder.smtpAuth;
		this.userAuth = builder.userAuth;
		this.passwordAuth = builder.passwordAuth;
		this.socketFactoryClass = builder.socketFactoryClass;
		this.socketFactoryPort = builder.socketFactoryPort;

		this.from = builder.from;
		this.personal = builder.personal;
		this.to = builder.to;
		this.cc = builder.cc;
		this.bcc = builder.bcc;
		this.subject = builder.subject;
		this.contentHtml = builder.contentHtml;
		this.body = builder.body;
		this.file = builder.file;
	}

	public Boolean send() throws AddressException, MessagingException,
			IOException {
		Boolean success = false;

		// Propiedades de la conexi\u00f3n
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.host", smtpHost);
		mailServerProperties.put("mail.smtp.port", smtpPort);
		mailServerProperties.put("mail.smtp.auth", smtpAuth.toString());

		if (socketFactoryPort != null) {
			if (!socketFactoryPort.equals("")) {
				mailServerProperties.put("mail.smtp.socketFactory.port",
						socketFactoryPort);
			}
		}

		if (socketFactoryClass != null) {
			if (!socketFactoryClass.equals("")) {
				mailServerProperties.put("mail.smtp.socketFactory.class",
						socketFactoryClass);
			}
		}

		if (smtpAuth) {
			// Autenticaci\u00f3n
			if (userAuth == null || userAuth.equals("") || passwordAuth == null
					|| passwordAuth.equals("")) {
				return success;
			}

			getMailSession = Session.getDefaultInstance(mailServerProperties,
					new javax.mail.Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userAuth,
									passwordAuth);
						}
					});

		} else {
			getMailSession = Session.getInstance(mailServerProperties);
		}

		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.setFrom(new InternetAddress(from,
				(personal == null || personal.equals("")) ? from : personal));
		if (to != null) {
			generateMailMessage.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
		}
		if (cc != null) {
			generateMailMessage.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(cc, false));
		}
		if (bcc != null) {
			generateMailMessage.setRecipients(Message.RecipientType.BCC,
					InternetAddress.parse(bcc, false));
		}
		generateMailMessage.setSubject(subject);

		if (file != null) {
			if (!file.equals("")) {
				MimeBodyPart mbp1 = new MimeBodyPart();
				mbp1.setContent(body, (contentHtml) ? "text/html"
						: "text/plain");
				MimeBodyPart mbp2 = new MimeBodyPart();
				mbp2.attachFile(file);
				MimeMultipart mp = new MimeMultipart();
				mp.addBodyPart(mbp1);
				mp.addBodyPart(mbp2);
				generateMailMessage.setContent(mp);
			}
		} else {
			generateMailMessage.setContent(body, (contentHtml) ? "text/html"
					: "text/plain");
		}

		generateMailMessage.setHeader("X-Mailer", "msgsend");
		generateMailMessage.setSentDate(new Date());

		javax.mail.Transport.send(generateMailMessage);
		return true;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public Boolean getSmtpAuth() {
		return smtpAuth;
	}

	public String getUserAuth() {
		return userAuth;
	}

	public String getPasswordAuth() {
		return passwordAuth;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public String getFrom() {
		return from;
	}

	public String getPersonal() {
		return personal;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public Boolean getContentHtml() {
		return contentHtml;
	}

	public String getBody() {
		return body;
	}

	public String getFile() {
		return file;
	}
}