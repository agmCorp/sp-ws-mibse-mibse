package com.bse.accesodatos.eviajeros;

import com.bse.accesodatos.esoa.Moneda;

public class Viajero {

	public static String COVID_SIN_COBERTURA = "N";
	public static String COVID_COBERTURA_BASICA = "B";
	public static String COVID_COBERTURA_PLUS = "P";
	
	private Float capitalMuerte;
	private Float coberturaPrexistentes;
	private Moneda moneda;
	private Float premio;
	private int edad;
	private String email;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String documento;
	private String celular;
	private String coberturaCovid;

	public Float getCapitalMuerte() {
		return capitalMuerte;
	}
	
	public void setCapitalMuerte(Float capitalMuerte) {
		this.capitalMuerte = capitalMuerte;
	}

	public Float getCoberturaPrexistentes() {
		return coberturaPrexistentes;
	}

	public void setCoberturaPrexistentes(Float coberturaPrexistentes) {
		this.coberturaPrexistentes = coberturaPrexistentes;
	}

	public Moneda getMoneda() {
		return moneda;
	}
	
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
	public Float getPremio() {
		return premio;
	}
	
	public void setPremio(Float premio) {
		this.premio = premio;
	}

	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}	

	public String getCoberturaCovid() {
		return coberturaCovid;
	}

	public void setCoberturaCovid(String coberturaCovid) {
		this.coberturaCovid = coberturaCovid;
	}

	public String toString(){
		return "Viajero(" + tipoDocumento + "|" + documento + "|" + celular + "|" + email + "|" + nombre + "|" + apellido + ")";
	}
}
