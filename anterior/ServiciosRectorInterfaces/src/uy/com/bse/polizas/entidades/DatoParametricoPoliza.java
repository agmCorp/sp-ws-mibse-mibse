package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class DatoParametricoPoliza implements Serializable{

	private static final long serialVersionUID = 8560224374176941531L;
	private String codDato;
	private String descripDato;
	private String valorDato;
	private Integer codTabla;
	private Integer cantidad;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	public String getDescripDato() {
		return descripDato;
	}
	public void setDescripDato(String descripDato) {
		this.descripDato = descripDato;
	}
	public String getValorDato() {
		return valorDato;
	}
	public void setValorDato(String valorDato) {
		this.valorDato = valorDato;
	}
	public Integer getCodTabla() {
		return codTabla;
	}
	public void setCodTabla(Integer codTabla) {
		this.codTabla = codTabla;
	}
	

}
