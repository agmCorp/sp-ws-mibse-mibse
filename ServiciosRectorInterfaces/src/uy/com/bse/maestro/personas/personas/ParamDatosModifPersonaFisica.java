package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosModifPersonaFisica extends ParamGenerico{
	private Integer codPersona;
	private String sexo;
	private String fechaNacimiento;
	private Integer codEstadoCivil;
	private Integer codProfesion;
	private String usuario;
	private Integer codDireccion;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getCodEstadoCivil() {
		return codEstadoCivil;
	}
	public void setCodEstadoCivil(Integer codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}
	public Integer getCodProfesion() {
		return codProfesion;
	}
	public void setCodProfesion(Integer codProfesion) {
		this.codProfesion = codProfesion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}	
}
