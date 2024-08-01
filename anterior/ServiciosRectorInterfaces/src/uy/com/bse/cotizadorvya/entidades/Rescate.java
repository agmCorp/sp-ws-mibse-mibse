package uy.com.bse.cotizadorvya.entidades;

import java.io.Serializable;

public class Rescate implements Serializable {	
	private static final long serialVersionUID = 8921500040252472587L;
	
	private Integer antiguedad;
	private Double valor;
	
	public Integer getAntiguedad() {
		return antiguedad;
	}
	
	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
