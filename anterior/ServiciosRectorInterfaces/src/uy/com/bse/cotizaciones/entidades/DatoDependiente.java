package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class DatoDependiente implements Serializable{
	
	private Integer codDato;
	private String valorDato;
	private Integer codBien;
	private String descValor;
	
	public Integer getCodDato() {
		return codDato;
	}
	public void setCodDato(Integer codDato) {
		this.codDato = codDato;
	}
	public String getValorDato() {
		return valorDato;
	}
	public void setValorDato(String valorDato) {
		this.valorDato = valorDato;
	}
	public Integer getCodBien() {
		return codBien;
	}
	public void setCodBien(Integer codBien) {
		this.codBien = codBien;
	}
	public String getDescValor() {
		return descValor;
	}
	public void setDescValor(String descValor) {
		this.descValor = descValor;
	}
	
	

}
