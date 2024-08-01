package uy.com.bse.dnic;

import java.io.Serializable;

public class ObjPersona implements Serializable {

	private static final long serialVersionUID = -9038059848311658366L;
	private String codTipoDocumento;
	private String nroDocumento;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String apellidoAdoptivo1;
	private String apellidoAdoptivo2;
	private int sexo;
	private String fechaNacimiento;
	private int codNacionalidad;
	private String nombreEnCedula;
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getApellidoAdoptivo1() {
		return apellidoAdoptivo1;
	}
	public void setApellidoAdoptivo1(String apellidoAdoptivo1) {
		this.apellidoAdoptivo1 = apellidoAdoptivo1;
	}
	public String getApellidoAdoptivo2() {
		return apellidoAdoptivo2;
	}
	public void setApellidoAdoptivo2(String apellidoAdoptivo2) {
		this.apellidoAdoptivo2 = apellidoAdoptivo2;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(int codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getNombreEnCedula() {
		return nombreEnCedula;
	}
	public void setNombreEnCedula(String nombreEnCedula) {
		this.nombreEnCedula = nombreEnCedula;
	}
}
