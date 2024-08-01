package uy.com.bse.encuesta;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class EncuestaPregunta implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private int order;
	private String texto;
	private LinkedHashMap<String, String> opciones; //Label, Value
	private String respuesta;
	
	
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public LinkedHashMap<String, String> getOpciones() {
		return opciones;
	}
	public void setOpciones(LinkedHashMap<String, String> opciones) {
		this.opciones = opciones;
	}
		
}
