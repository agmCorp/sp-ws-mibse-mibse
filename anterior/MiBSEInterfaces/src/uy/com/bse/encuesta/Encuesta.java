package uy.com.bse.encuesta;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encuesta implements Serializable {


	private static final long serialVersionUID = 5500122791684893346L;
	private String id;
	private String actorId; //se utiliza para luego consultar si se grabo la encuesta.
	private String tituloMensajeBienvenida;
	private String mensajeBienvenida;
	private String mensajeDespedida;
	private String tituloMensajeDespedida;
	private Map<String, String> camposAcordados; 
	private List<EncuestaPregunta> preguntas;
	private List<Memo> memos;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTituloMensajeBienvenida() {
		return tituloMensajeBienvenida;
	}
	public void setTituloMensajeBienvenida(String tituloMensajeBienvenida) {
		this.tituloMensajeBienvenida = tituloMensajeBienvenida;
	}
	public String getMensajeBienvenida() {
		return mensajeBienvenida;
	}
	public void setMensajeBienvenida(String mensajeBienvenida) {
		this.mensajeBienvenida = mensajeBienvenida;
	}
	public List<EncuestaPregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<EncuestaPregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Memo> getMemos() {
		return memos;
	}
	public void setMemos(List<Memo> memos) {
		this.memos = memos;
	}
	
	public Map<String, String> getCamposAcordados() {
		return camposAcordados;
	}
	public void setCamposAcordados(Map<String, String> camposAcordados) {
		this.camposAcordados = camposAcordados;
	}
	public String getMensajeDespedida() {
		return mensajeDespedida;
	}
	public void setMensajeDespedida(String mensajeDespedida) {
		this.mensajeDespedida = mensajeDespedida;
	}
	public String getTituloMensajeDespedida() {
		return tituloMensajeDespedida;
	}
	public void setTituloMensajeDespedida(String tituloMensajeDespedida) {
		this.tituloMensajeDespedida = tituloMensajeDespedida;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	
	
		
	
}
