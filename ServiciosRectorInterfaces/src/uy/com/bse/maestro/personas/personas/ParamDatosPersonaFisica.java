package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosPersonaFisica extends ParamGenerico{
	private String tipoDocumento;
	private String numDocumento;
	private String apellido;
	private String nombre;
	private Integer codDireccion;
	private String sexo;
	private String fechaNacimiento;
	private String codEstadoCivil;
	private Integer codProfesion;
	private Integer codPersona;
	private String rut;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
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
	public String getCodEstadoCivil() {
		return codEstadoCivil;
	}
	public void setCodEstadoCivil(String codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}
	public Integer getCodProfesion() {
		return codProfesion;
	}
	public void setCodProfesion(Integer codProfesion) {
		this.codProfesion = codProfesion;
	}	
}
