package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class DatosCalculoRenta implements Serializable{
	private String descripDato;
	private Double codValor;
	private String descripValor;
	
	public String getDescripDato() {
		return descripDato;
	}
	public void setDescripDato(String descripDato) {
		this.descripDato = descripDato;
	}
	public Double getCodValor() {
		return codValor;
	}
	public void setCodValor(Double codValor) {
		this.codValor = codValor;
	}
	public String getDescripValor() {
		return descripValor;
	}
	public void setDescripValor(String descripValor) {
		this.descripValor = descripValor;
	}

}
