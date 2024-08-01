package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Cuota implements Serializable{
	private Integer numEndoso;
	private String fechaEmision;
	private String fechaHasta;
	private String descripTipoTransaccion;
	private String descripEstado;
	private String numCuota;
	private Double cantPrimaPura;
	private Double cantPrima;
	private Double cantFacturar;
	private String anulada;
	private String motivo; 
	private Integer factura; 
	private Double premio; 

	
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getDescripTipoTransaccion() {
		return descripTipoTransaccion;
	}
	public void setDescripTipoTransaccion(String descripTipoTransaccion) {
		this.descripTipoTransaccion = descripTipoTransaccion;
	}
	public String getDescripEstado() {
		return descripEstado;
	}
	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	public String getNumCuota() {
		return numCuota;
	}
	public void setNumCuota(String numCuota) {
		this.numCuota = numCuota;
	}
	public Double getCantPrimaPura() {
		return cantPrimaPura;
	}
	public void setCantPrimaPura(Double cantPrimaPura) {
		this.cantPrimaPura = cantPrimaPura;
	}
	public Double getCantPrima() {
		return cantPrima;
	}
	public void setCantPrima(Double cantPrima) {
		this.cantPrima = cantPrima;
	}
	public Double getCantFacturar() {
		return cantFacturar;
	}
	public void setCantFacturar(Double cantFacturar) {
		this.cantFacturar = cantFacturar;
	}
	public String getAnulada() {
		return anulada;
	}
	public void setAnulada(String anulada) {
		this.anulada = anulada;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Integer getFactura() {
		return factura;
	}
	public void setFactura(Integer factura) {
		this.factura = factura;
	}
	public Double getPremio() {
		return premio;
	}
	public void setPremio(Double premio) {
		this.premio = premio;
	}
	
}
