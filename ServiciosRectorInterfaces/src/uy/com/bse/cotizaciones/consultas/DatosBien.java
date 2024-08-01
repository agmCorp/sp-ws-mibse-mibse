package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class DatosBien implements Serializable{
	private String datoDesc;
	private String valorCod;
	private String valorDesc;
	private String datoTipo;
	private String visibilidad;
	private String requerido;
	
	private String codDato;
	private String longitudDato;
	private String cantDecimales;
	private String codTabla;
	private String colorNotificacion;
	
	
	
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	public String getLongitudDato() {
		return longitudDato;
	}
	public void setLongitudDato(String longitudDato) {
		this.longitudDato = longitudDato;
	}
	public String getCantDecimales() {
		return cantDecimales;
	}
	public void setCantDecimales(String cantDecimales) {
		this.cantDecimales = cantDecimales;
	}
	public String getCodTabla() {
		return codTabla;
	}
	public void setCodTabla(String codTabla) {
		this.codTabla = codTabla;
	}
	public String getDatoDesc() {
		return datoDesc;
	}
	public void setDatoDesc(String datoDesc) {
		this.datoDesc = datoDesc;
	}
	public String getValorCod() {
		return valorCod;
	}
	public void setValorCod(String valorCod) {
		this.valorCod = valorCod;
	}
	public String getValorDesc() {
		return valorDesc;
	}
	public void setValorDesc(String valorDesc) {
		this.valorDesc = valorDesc;
	}
	public String getDatoTipo() {
		return datoTipo;
	}
	public void setDatoTipo(String datoTipo) {
		this.datoTipo = datoTipo;
	}
	public String getVisibilidad() {
		return visibilidad;
	}
	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	public String getColorNotificacion() {
		return colorNotificacion;
	}
	public void setColorNotificacion(String colorNotificacion) {
		this.colorNotificacion = colorNotificacion;
	}
	
	
}
