package uy.com.bse.serviciosweb.util.mail;

import apimailws.apimailwsclient.EnviarMailRequest.Adjuntos;
import apimailws.apimailwsclient.EnviarMailRequest.Metadatos;

public class ApiMailWSBuilder {
	Adjuntos adjuntos;
	String asunto;
	String cuerpo;
	String destinatario;
	String lista;
	Metadatos metadatos;
	String nombreRemitente;
	String remitente;
	
	public static ApiMailWSBuilder create() {
		return new ApiMailWSBuilder();
	}

	public ApiMailWSBuilder withAdjuntos(Adjuntos adjuntos) {
		this.adjuntos = adjuntos;
		return this;
	}
	
	public ApiMailWSBuilder withAsunto(String asunto) {
		this.asunto = asunto;
		return this;
	}

	public ApiMailWSBuilder withCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
		return this;
	}

	public ApiMailWSBuilder withDestinatario(String destinatario) {
		this.destinatario = destinatario;
		return this;
	}

	public ApiMailWSBuilder withLista(String lista) {
		this.lista = lista;
		return this;
	}

	public ApiMailWSBuilder withMetadatos(Metadatos metadatos) {
		this.metadatos = metadatos;
		return this;
	}

	public ApiMailWSBuilder withNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
		return this;
	}

	public ApiMailWSBuilder withRemitente(String remitente) {
		this.remitente = remitente;
		return this;
	}
	
	public ApiMailWSClientHelper build() {
		return new ApiMailWSClientHelper(this);
	}
}
