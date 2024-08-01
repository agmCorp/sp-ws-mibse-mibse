package uy.com.bse.maestro.personas.consultas;

import java.io.Serializable;

public class DatosPersonaFisica implements Serializable{
	private Integer codPersona;
	private String apellido;
	private String tipoDocumento;
	private String numDocumento;
	private Integer cantRoles;
	private String nombre;
	private String codCliente;
	
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
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
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public Integer getCantRoles() {
		return cantRoles;
	}
	public void setCantRoles(Integer cantRoles) {
		this.cantRoles = cantRoles;
	}	
}
