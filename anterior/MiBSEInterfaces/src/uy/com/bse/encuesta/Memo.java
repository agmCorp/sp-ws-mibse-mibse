package uy.com.bse.encuesta;

import java.io.Serializable;

public class Memo implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String id;
	private int order;
	private String texto;
	private String respuesta;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
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
	

}
