package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class DatosTotalesCuotas implements Serializable{
	private Double premioEmitido;
	private Double premioFacturado;
	private Double saldoPremio;
	private Double saldoPremioVencido;
	private Double totalFacturado;
	private Double totalCobrado;
	private Double preliqPendiente;
	private Double saldoTotal;
	private Double saldoTotalVencido;
	private Double totalComision;
	private Double comisionLiberada;
	private Double totalComisionLiberada;
	
	public Double getPremioEmitido() {
		return premioEmitido;
	}
	public void setPremioEmitido(Double premioEmitido) {
		this.premioEmitido = premioEmitido;
	}
	public Double getPremioFacturado() {
		return premioFacturado;
	}
	public void setPremioFacturado(Double premioFacturado) {
		this.premioFacturado = premioFacturado;
	}
	public Double getSaldoPremio() {
		return saldoPremio;
	}
	public void setSaldoPremio(Double saldoPremio) {
		this.saldoPremio = saldoPremio;
	}
	public Double getSaldoPremioVencido() {
		return saldoPremioVencido;
	}
	public void setSaldoPremioVencido(Double saldoPremioVencido) {
		this.saldoPremioVencido = saldoPremioVencido;
	}
	public Double getTotalFacturado() {
		return totalFacturado;
	}
	public void setTotalFacturado(Double totalFacturado) {
		this.totalFacturado = totalFacturado;
	}
	public Double getTotalCobrado() {
		return totalCobrado;
	}
	public void setTotalCobrado(Double totalCobrado) {
		this.totalCobrado = totalCobrado;
	}
	public Double getPreliqPendiente() {
		return preliqPendiente;
	}
	public void setPreliqPendiente(Double preliqPendiente) {
		this.preliqPendiente = preliqPendiente;
	}
	public Double getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	public Double getSaldoTotalVencido() {
		return saldoTotalVencido;
	}
	public void setSaldoTotalVencido(Double saldoTotalVencido) {
		this.saldoTotalVencido = saldoTotalVencido;
	}
	public Double getTotalComision() {
		return totalComision;
	}
	public void setTotalComision(Double totalComision) {
		this.totalComision = totalComision;
	}
	public Double getComisionLiberada() {
		return comisionLiberada;
	}
	public void setComisionLiberada(Double comisionLiberada) {
		this.comisionLiberada = comisionLiberada;
	}
	public Double getTotalComisionLiberada() {
		return totalComisionLiberada;
	}
	public void setTotalComisionLiberada(Double totalComisionLiberada) {
		this.totalComisionLiberada = totalComisionLiberada;
	} 

}
