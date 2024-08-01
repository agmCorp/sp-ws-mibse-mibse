package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerClientesSolicitante extends ParamGenerico {
	private String apellidoRazon;
	private String nombre;
	private String codCliente;
	private String tipoDocumento;
	private String numDocumento;
	private String rut;
	
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
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
		
}
