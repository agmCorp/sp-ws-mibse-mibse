package uy.com.bse.maestro.personas.personas;

import java.io.Serializable;

public class DatosDirecciones implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 412060200434867438L;
	private Integer codDireccion;
	private Integer tipoDomicilio; 
	private String descripTipoDomicilio;
	private String direccion;
	private String fechaActualizacion;
	private String tienePolizasAsociadas;
	private String aclaraciones;
	
	
	
	public String getAclaraciones() {
		return aclaraciones;
	}
	public void setAclaraciones(String aclaraciones) {
		this.aclaraciones = aclaraciones;
	}
	public String getTienePolizasAsociadas() {
		return tienePolizasAsociadas;
	}
	public void setTienePolizasAsociadas(String tienePolizasAsociadas) {
		this.tienePolizasAsociadas = tienePolizasAsociadas;
	}
	public Integer getTipoDomicilio() {
		return tipoDomicilio;
	}
	public void setTipoDomicilio(Integer tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}
	public String getDescripTipoDomicilio() {
		return descripTipoDomicilio;
	}
	public void setDescripTipoDomicilio(String descripTipoDomicilio) {
		this.descripTipoDomicilio = descripTipoDomicilio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}
	
}
