package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class InfoSiniestro implements Serializable{
	private Integer numTercero;
	private Integer numAsegurado;
	private String descripBien;
	private Integer codRamo;
	private String codCobertura;
	private String codTipoReserva;
	private Integer codHechoGen; 
	private String descripMoneda;
	private String estadoSiniestro;
	private String descripEstado;
	private Double montoReserva;
	private Double montoCosto;
	
	public Integer getNumTercero() {
		return numTercero;
	}
	public void setNumTercero(Integer numTercero) {
		this.numTercero = numTercero;
	}
	public Integer getNumAsegurado() {
		return numAsegurado;
	}
	public void setNumAsegurado(Integer numAsegurado) {
		this.numAsegurado = numAsegurado;
	}
	public String getDescripBien() {
		return descripBien;
	}
	public void setDescripBien(String descripBien) {
		this.descripBien = descripBien;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodCobertura() {
		return codCobertura;
	}
	public void setCodCobertura(String codCobertura) {
		this.codCobertura = codCobertura;
	}
	public String getCodTipoReserva() {
		return codTipoReserva;
	}
	public void setCodTipoReserva(String codTipoReserva) {
		this.codTipoReserva = codTipoReserva;
	}
	public Integer getCodHechoGen() {
		return codHechoGen;
	}
	public void setCodHechoGen(Integer codHechoGen) {
		this.codHechoGen = codHechoGen;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public String getEstadoSiniestro() {
		return estadoSiniestro;
	}
	public void setEstadoSiniestro(String estadoSiniestro) {
		this.estadoSiniestro = estadoSiniestro;
	}
	public String getDescripEstado() {
		return descripEstado;
	}
	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	public Double getMontoReserva() {
		return montoReserva;
	}
	public void setMontoReserva(Double montoReserva) {
		this.montoReserva = montoReserva;
	}
	public Double getMontoCosto() {
		return montoCosto;
	}
	public void setMontoCosto(Double montoCosto) {
		this.montoCosto = montoCosto;
	}

}
