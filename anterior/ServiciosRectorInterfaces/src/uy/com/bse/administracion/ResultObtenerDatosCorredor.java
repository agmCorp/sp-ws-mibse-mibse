package uy.com.bse.administracion;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosCorredor extends ResultGenerico{

	
	/*Nombre, Apellido, Nro. Persona, Código Corredor, Indicador de adhesión a e-volante y mail
	 * 
	 * 
	 */
	
	private String nombre;
	private String apellido;
	private Integer nroPersona;
	private Integer codCorredor;
	private String adhesionEvolante;
	private String email;
	
	
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
	public Integer getNroPersona() {
		return nroPersona;
	}
	public void setNroPersona(Integer nroPersona) {
		this.nroPersona = nroPersona;
	}
	public Integer getCodCorredor() {
		return codCorredor;
	}
	public void setCodCorredor(Integer codCorredor) {
		this.codCorredor = codCorredor;
	}
	public String getAdhesionEvolante() {
		return adhesionEvolante;
	}
	public void setAdhesionEvolante(String adhesionEvolante) {
		this.adhesionEvolante = adhesionEvolante;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
