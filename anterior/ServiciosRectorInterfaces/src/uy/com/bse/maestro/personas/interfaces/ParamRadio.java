package uy.com.bse.maestro.personas.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamRadio extends ParamGenerico{
	private String codPais;
	private String codDepartamento;
	private String codLocalidad;
	private String numPostal;
	private String numRadio;
	private String codCalle;
	private String descripCalle;
	private String numPuerta;
	private String descripDepartamento;
	private String descripLocalidad;
	
	public String getCodPais() {
		return codPais;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getCodLocalidad() {
		return codLocalidad;
	}
	public void setCodLocalidad(String codLocalidad) {
		this.codLocalidad = codLocalidad;
	}
	public String getNumPostal() {
		return numPostal;
	}
	public void setNumPostal(String numPostal) {
		this.numPostal = numPostal;
	}
	public String getNumRadio() {
		return numRadio;
	}
	public void setNumRadio(String numRadio) {
		this.numRadio = numRadio;
	}
	public String getCodCalle() {
		return codCalle;
	}
	public void setCodCalle(String codCalle) {
		this.codCalle = codCalle;
	}
	public String getDescripCalle() {
		return descripCalle;
	}
	public void setDescripCalle(String descripCalle) {
		this.descripCalle = descripCalle;
	}
	public String getNumPuerta() {
		return numPuerta;
	}
	public void setNumPuerta(String numPuerta) {
		this.numPuerta = numPuerta;
	}
	public String getDescripDepartamento() {
		return descripDepartamento;
	}
	public void setDescripDepartamento(String descripDepartamento) {
		this.descripDepartamento = descripDepartamento;
	}
	public String getDescripLocalidad() {
		return descripLocalidad;
	}
	public void setDescripLocalidad(String descripLocalidad) {
		this.descripLocalidad = descripLocalidad;
	}
	
	

}
