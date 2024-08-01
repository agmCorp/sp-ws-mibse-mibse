package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultExistePersona extends ResultGenerico{
	
	private Integer numPersona;
	private String codCliente;
	private String rut;
	private String tipoDoc;
	private String numeroDocumento;
	private String nombre;
	private String apellidoRazon;
	private Integer tipoPersona;
	private String descripTipoPersona;
	private String descripTipoDocumento;
	private String esCliente;
	
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoRazon() {
		return apellidoRazon;
	}
	public void setApellidoRazon(String apellidoRazon) {
		this.apellidoRazon = apellidoRazon;
	}
	public Integer getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(Integer tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getDescripTipoPersona() {
		return descripTipoPersona;
	}
	public void setDescripTipoPersona(String descripTipoPersona) {
		this.descripTipoPersona = descripTipoPersona;
	}
	public String getDescripTipoDocumento() {
		return descripTipoDocumento;
	}
	public void setDescripTipoDocumento(String descripTipoDocumento) {
		this.descripTipoDocumento = descripTipoDocumento;
	}
	public String getEsCliente() {
		return esCliente;
	}
	public void setEsCliente(String esCliente) {
		this.esCliente = esCliente;
	}
		
}
