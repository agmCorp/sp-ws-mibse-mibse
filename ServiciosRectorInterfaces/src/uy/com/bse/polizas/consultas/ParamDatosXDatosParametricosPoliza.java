package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosXDatosParametricosPoliza extends ParamGenerico{
	
	private Integer codRamo;
	private Integer numPoliza;
	private Integer codDato;
	private String valorDato;
	
	
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
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
	
	

}
