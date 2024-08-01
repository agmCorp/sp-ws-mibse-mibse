package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class DatosBasicosSiniestro implements Serializable{
	private Integer numPoliza;
	private Integer numCertificado;
	private Integer anio;
	private Integer codRamo;
	private Integer numSiniestro;
	private Integer numEndoso; 
	private String enlace;
	private String fechaOcurrencia;
	private String fechaDeclaracion;
	private String codNacionalidad;
	private Integer codCliente;
	private String cliente;
	private String descripEjecutivo;
	private String asegurado;

	
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumSiniestro() {
		return numSiniestro;
	}
	public void setNumSiniestro(Integer numSiniestro) {
		this.numSiniestro = numSiniestro;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public String getFechaOcurrencia() {
		return fechaOcurrencia;
	}
	public void setFechaOcurrencia(String fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}
	public String getFechaDeclaracion() {
		return fechaDeclaracion;
	}
	public void setFechaDeclaracion(String fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public Integer getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getDescripEjecutivo() {
		return descripEjecutivo;
	}
	public void setDescripEjecutivo(String descripEjecutivo) {
		this.descripEjecutivo = descripEjecutivo;
	}
	public String getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}
		
}
