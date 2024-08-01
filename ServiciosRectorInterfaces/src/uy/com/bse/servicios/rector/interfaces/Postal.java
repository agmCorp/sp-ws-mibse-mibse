package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Postal implements Serializable{
	private String radio;
	private String codCalle;
	private String descCalle;
	private String numInicio;
	private String numFinal;
	private String numPostal;
	private String codLocalidad;
	private String codDepartamento;
	private String descDepartamento;
	private String descLocalidad;
	private String codPostal;
	private String localidad;
	private String numRadio;
	private String paridad;
	
	
	public String getParidad() {
		return paridad;
	}
	public void setParidad(String paridad) {
		this.paridad = paridad;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getCodCalle() {
		return codCalle;
	}
	public void setCodCalle(String codCalle) {
		this.codCalle = codCalle;
	}
	public String getDescCalle() {
		return descCalle;
	}
	public void setDescCalle(String descCalle) {
		this.descCalle = descCalle;
	}
	public String getNumInicio() {
		return numInicio;
	}
	public void setNumInicio(String numInicio) {
		this.numInicio = numInicio;
	}
	public String getNumFinal() {
		return numFinal;
	}
	public void setNumFinal(String numFinal) {
		this.numFinal = numFinal;
	}
	public String getNumPostal() {
		return numPostal;
	}
	public void setNumPostal(String numPostal) {
		this.numPostal = numPostal;
	}
	public String getCodLocalidad() {
		return codLocalidad;
	}
	public void setCodLocalidad(String codLocalidad) {
		this.codLocalidad = codLocalidad;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getDescDepartamento() {
		return descDepartamento;
	}
	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}
	public String getDescLocalidad() {
		return descLocalidad;
	}
	public void setDescLocalidad(String descLocalidad) {
		this.descLocalidad = descLocalidad;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getNumRadio() {
		return numRadio;
	}
	public void setNumRadio(String numRadio) {
		this.numRadio = numRadio;
	}
	
}
