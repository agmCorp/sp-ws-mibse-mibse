package uy.com.bse.maestro.personas.personas;

import java.io.Serializable;

public class PersonaFisica implements Serializable{
	private Integer codPersona;
	private String apellido;
	private String nombre;
	private String tipoDocumento;
	private String numDocumento;	
	private String sexo;
	private Integer codDireccionHabitual;
	private String codEstadoCivil;
	private String fechaNacimiento;
	private Integer codProfesion;
	private String fechaAlta;
	private String fechaBaja;
	private Integer codMotivoBaja;
	private String fechaActualizacion;
	private String descripTipoDocumento;
	private String descripDireccionHabitual;
	private String descripEstadoCivil;
	private String descripProfesion;
	private String descripMotivoBaja;
	private String usuario;
	private String rut;
	private String codCliente;
	private String esPEP;
	private String fechaDiligencia;
	
	public String getEsPEP() {
		return esPEP;
	}
	public void setEsPEP(String esPEP) {
		this.esPEP = esPEP;
	}
	public String getFechaDiligencia() {
		return fechaDiligencia;
	}
	public void setFechaDiligencia(String fechaDiligencia) {
		this.fechaDiligencia = fechaDiligencia;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDescripTipoDocumento() {
		return descripTipoDocumento;
	}
	public void setDescripTipoDocumento(String descripTipoDocumento) {
		this.descripTipoDocumento = descripTipoDocumento;
	}
	public String getDescripDireccionHabitual() {
		return descripDireccionHabitual;
	}
	public void setDescripDireccionHabitual(String descripDireccionHabitual) {
		this.descripDireccionHabitual = descripDireccionHabitual;
	}
	public String getDescripEstadoCivil() {
		return descripEstadoCivil;
	}
	public void setDescripEstadoCivil(String descripEstadoCivil) {
		this.descripEstadoCivil = descripEstadoCivil;
	}
	public String getDescripProfesion() {
		return descripProfesion;
	}
	public void setDescripProfesion(String descripProfesion) {
		this.descripProfesion = descripProfesion;
	}
	public String getDescripMotivoBaja() {
		return descripMotivoBaja;
	}
	public void setDescripMotivoBaja(String descripMotivoBaja) {
		this.descripMotivoBaja = descripMotivoBaja;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getCodDireccionHabitual() {
		return codDireccionHabitual;
	}
	public void setCodDireccionHabitual(Integer codDireccionHabitual) {
		this.codDireccionHabitual = codDireccionHabitual;
	}
	public String getCodEstadoCivil() {
		return codEstadoCivil;
	}
	public void setCodEstadoCivil(String codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getCodProfesion() {
		return codProfesion;
	}
	public void setCodProfesion(Integer codProfesion) {
		this.codProfesion = codProfesion;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Integer getCodMotivoBaja() {
		return codMotivoBaja;
	}
	public void setCodMotivoBaja(Integer codMotivoBaja) {
		this.codMotivoBaja = codMotivoBaja;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
}
