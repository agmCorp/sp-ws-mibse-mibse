package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class CertificadoPolizaBasico implements Serializable{
	private Integer numCertificado;
	private String asegurado;
	private String estadoPoliza;
	private String descripMedioPago;
	private String descripOrigen;
	private String descripPlanPago;
	private String fechaDesdeVigencia;
	
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}
	public String getEstadoPoliza() {
		return estadoPoliza;
	}
	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}
	public String getDescripMedioPago() {
		return descripMedioPago;
	}
	public void setDescripMedioPago(String descripMedioPago) {
		this.descripMedioPago = descripMedioPago;
	}
	public String getDescripOrigen() {
		return descripOrigen;
	}
	public void setDescripOrigen(String descripOrigen) {
		this.descripOrigen = descripOrigen;
	}
	public String getDescripPlanPago() {
		return descripPlanPago;
	}
	public void setDescripPlanPago(String descripPlanPago) {
		this.descripPlanPago = descripPlanPago;
	}
	public String getFechaDesdeVigencia() {
		return fechaDesdeVigencia;
	}
	public void setFechaDesdeVigencia(String fechaDesdeVigencia) {
		this.fechaDesdeVigencia = fechaDesdeVigencia;
	}
	
}
