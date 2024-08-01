package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerMapaMsgSiniestro extends ResultGenerico{

	private static final long serialVersionUID = 6033134603484763981L;
	
	private int idMsg;
	private String tituloMsg;
	private String estadoMsg;
	private String descripcionMsg;
	
	private String titulofuturoMsg;
	private String futuroMsg;
	
	private String finalMsg; //S o N
	private int carril;
	private String etapa;
	private String tiempoPromedio;
	
	
	
	public String getTituloMsg() {
		return tituloMsg;
	}
	public void setTituloMsg(String tituloMsg) {
		this.tituloMsg = tituloMsg;
	}
	public String getDescripcionMsg() {
		return descripcionMsg;
	}
	public void setDescripcionMsg(String descripcionMsg) {
		this.descripcionMsg = descripcionMsg;
	}
	public String getFuturoMsg() {
		return futuroMsg;
	}
	public void setFuturoMsg(String futuroMsg) {
		this.futuroMsg = futuroMsg;
	}
	public int getCarril() {
		return carril;
	}
	public void setCarril(int carril) {
		this.carril = carril;
	}
	public int getIdMsg() {
		return idMsg;
	}
	public void setIdMsg(int idMsg) {
		this.idMsg = idMsg;
	}
	public String getEstadoMsg() {
		return estadoMsg;
	}
	public void setEstadoMsg(String estadoMsg) {
		this.estadoMsg = estadoMsg;
	}
	public String getTitulofuturoMsg() {
		return titulofuturoMsg;
	}
	public void setTitulofuturoMsg(String titulofuturoMsg) {
		this.titulofuturoMsg = titulofuturoMsg;
	}
	public String getFinalMsg() {
		return finalMsg;
	}
	public void setFinalMsg(String finalMsg) {
		this.finalMsg = finalMsg;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getTiempoPromedio() {
		return tiempoPromedio;
	}
	public void setTiempoPromedio(String tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}	

}