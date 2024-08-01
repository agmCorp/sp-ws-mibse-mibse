package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValoresDatosParametricosPoliza extends ParamGenerico{

	private Integer codRamo;
	private Integer numPoliza;
	private Integer codDato;
	
	
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
	
	
}
