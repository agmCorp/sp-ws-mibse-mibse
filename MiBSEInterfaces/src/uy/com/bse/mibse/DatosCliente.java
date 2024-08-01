package uy.com.bse.mibse;

import java.io.Serializable;
import java.util.ArrayList;

public class DatosCliente implements Serializable {
	private static final long serialVersionUID = -7298285980879241297L;

	private Integer numPersona;
	private String apellido;
	private String nombre;
	private String codTipoDocumento;
	private String descTipoDocumento;
	private String numDocumento;	
	private String codSexo;
	private String descSexo;
	private ArrayList<DatosComunicacion> comunicaciones = new ArrayList<DatosComunicacion>(5);
	
	private String fechaNacimiento;
	private Integer codProfesion;
	private String descProfesion;
	private String rut;
	private String razonSocial;
	private String numCI; // para el caso de las unipersonales que ademas de RUT tienen CI
	
	
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
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}
	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getCodSexo() {
		return codSexo;
	}
	public void setCodSexo(String codSexo) {
		this.codSexo = codSexo;
	}
	public String getDescSexo() {
		return descSexo;
	}
	public void setDescSexo(String descSexo) {
		this.descSexo = descSexo;
	}
	public ArrayList<DatosComunicacion> getComunicaciones() {
		return comunicaciones;
	}
	public void setComunicaciones(ArrayList<DatosComunicacion> comunicaciones) {
		this.comunicaciones = comunicaciones;
	}
	
	public Integer getCodProfesion() {
		return codProfesion;
	}
	public void setCodProfesion(Integer codProfesion) {
		this.codProfesion = codProfesion;
	}
	public String getDescProfesion() {
		return descProfesion;
	}
	public void setDescProfesion(String descProfesion) {
		this.descProfesion = descProfesion;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public void setUno(DatosComunicacion comunicacion){
		this.comunicaciones.add(comunicacion);
	}

	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNumCI() {
		return numCI;
	}
	public void setNumCI(String numCI) {
		this.numCI = numCI;
	}
	
}
