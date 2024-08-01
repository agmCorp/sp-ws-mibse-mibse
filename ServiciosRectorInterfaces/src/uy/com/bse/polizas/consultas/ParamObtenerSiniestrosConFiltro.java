package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerSiniestrosConFiltro extends ParamGenerico{
	private static final long serialVersionUID = 6398060118274617056L;
	private String fechaOcurrencia;
	private String fechaEntrada;
	private String numPoliza;
	private String codAsegurado;
	private String codEjecutivo;
	private String fueraPauta;
	private String asegurado;
	private String descripEjecutivo;
	private String codRamo;
	private String numSiniestro;
	private String numCertificado;
	private String anio;
	private String codProductor;
	
	public String getFechaOcurrencia() {
		return fechaOcurrencia;
	}
	public void setFechaOcurrencia(String fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodAsegurado() {
		return codAsegurado;
	}
	public void setCodAsegurado(String codAsegurado) {
		this.codAsegurado = codAsegurado;
	}
	public String getCodEjecutivo() {
		return codEjecutivo;
	}
	public void setCodEjecutivo(String codEjecutivo) {
		this.codEjecutivo = codEjecutivo;
	}
	public String getFueraPauta() {
		return fueraPauta;
	}
	public void setFueraPauta(String fueraPauta) {
		this.fueraPauta = fueraPauta;
	}
	public String getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}
	public String getDescripEjecutivo() {
		return descripEjecutivo;
	}
	public void setDescripEjecutivo(String descripEjecutivo) {
		this.descripEjecutivo = descripEjecutivo;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getNumSiniestro() {
		return numSiniestro;
	}
	public void setNumSiniestro(String numSiniestro) {
		this.numSiniestro = numSiniestro;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}		
}
