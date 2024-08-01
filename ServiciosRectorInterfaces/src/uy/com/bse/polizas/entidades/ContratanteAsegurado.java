package uy.com.bse.polizas.entidades;

import java.io.Serializable;

import uy.com.bse.maestro.personas.domicilio.DireccionEC;

public class ContratanteAsegurado implements Serializable{
	private String codNacionalidad;
	private String codCliente;
	private String nombrePersona;
	private String rut;
	private Integer codPersoneria;
	private String descripPersoneria;
	private String codCondicionIva;
	private	String descripCondicionIva;
	private	String codTarjeta;
	private	String descripTarjeta;
	private	DireccionEC direccionCobro;
	private	DireccionEC direccionEnvio;
	
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public Integer getCodPersoneria() {
		return codPersoneria;
	}
	public void setCodPersoneria(Integer codPersoneria) {
		this.codPersoneria = codPersoneria;
	}
	public String getDescripPersoneria() {
		return descripPersoneria;
	}
	public void setDescripPersoneria(String descripPersoneria) {
		this.descripPersoneria = descripPersoneria;
	}
	public String getCodCondicionIva() {
		return codCondicionIva;
	}
	public void setCodCondicionIva(String codCondicionIva) {
		this.codCondicionIva = codCondicionIva;
	}
	public String getDescripCondicionIva() {
		return descripCondicionIva;
	}
	public void setDescripCondicionIva(String descripCondicionIva) {
		this.descripCondicionIva = descripCondicionIva;
	}
	public String getCodTarjeta() {
		return codTarjeta;
	}
	public void setCodTarjeta(String codTarjeta) {
		this.codTarjeta = codTarjeta;
	}
	public String getDescripTarjeta() {
		return descripTarjeta;
	}
	public void setDescripTarjeta(String descripTarjeta) {
		this.descripTarjeta = descripTarjeta;
	}
	public DireccionEC getDireccionCobro() {
		return direccionCobro;
	}
	public void setDireccionCobro(DireccionEC direccionCobro) {
		this.direccionCobro = direccionCobro;
	}
	public DireccionEC getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(DireccionEC direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
}
