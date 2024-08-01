package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class DatosBasicosCliente implements Serializable{
	private Integer numPersona;
	private String apellidoRazonSocial;
	private String nombre;
	private String rut;
	private String tipoDocumento;
	private String descripTipoDocumento;
	private String numDocumento;
	
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getApellidoRazonSocial() {
		return apellidoRazonSocial;
	}
	public void setApellidoRazonSocial(String apellidoRazonSocial) {
		this.apellidoRazonSocial = apellidoRazonSocial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescripTipoDocumento() {
		return descripTipoDocumento;
	}
	public void setDescripTipoDocumento(String descripTipoDocumento) {
		this.descripTipoDocumento = descripTipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

			

}
