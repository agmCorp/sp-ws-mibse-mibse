package uy.com.bse.maestro.personas.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPersonasAlta extends ParamGenerico{
	private String apellidoRazon;
	private String nombre;
	private String rut;
	private String tipoDoc;
	private String numDocumento;
	private String tipoPersona;
	
	public String getApellidoRazon() {
		return apellidoRazon;
	}
	public void setApellidoRazon(String apellidoRazon) {
		this.apellidoRazon = apellidoRazon;
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
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
}
