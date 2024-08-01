package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValoresDatosParametricosEndoso extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer codDato;
	private Integer numPoliza;
	private Integer codRamo;
	private Boolean esUltimoEndoso;
	
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getCodDato() {
		return codDato;
	}
	public void setCodDato(Integer codDato) {
		this.codDato = codDato;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Boolean getEsUltimoEndoso() {
		return esUltimoEndoso;
	}
	public void setEsUltimoEndoso(Boolean esUltimoEndoso) {
		this.esUltimoEndoso = esUltimoEndoso;
	}
	
	
}
