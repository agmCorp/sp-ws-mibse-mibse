package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class RemesaAutomatica implements Serializable{
	private String numOrigen;
	private Integer numValor;
	private String descripDatos;
	
	public String getNumOrigen() {
		return numOrigen;
	}
	public void setNumOrigen(String numOrigen) {
		this.numOrigen = numOrigen;
	}
	public Integer getNumValor() {
		return numValor;
	}
	public void setNumValor(Integer numValor) {
		this.numValor = numValor;
	}
	public String getDescripDatos() {
		return descripDatos;
	}
	public void setDescripDatos(String descripDatos) {
		this.descripDatos = descripDatos;
	}

}
