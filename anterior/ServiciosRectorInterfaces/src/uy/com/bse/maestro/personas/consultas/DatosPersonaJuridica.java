package uy.com.bse.maestro.personas.consultas;

import java.io.Serializable;

public class DatosPersonaJuridica implements Serializable{
	private Integer codPersona;
	private String razonSocial;
	private String Ruc;
	private Integer cantRoles;
	private String codCliente;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return Ruc;
	}
	public void setRuc(String ruc) {
		Ruc = ruc;
	}
	public Integer getCantRoles() {
		return cantRoles;
	}
	public void setCantRoles(Integer cantRoles) {
		this.cantRoles = cantRoles;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}	
	
	
}
