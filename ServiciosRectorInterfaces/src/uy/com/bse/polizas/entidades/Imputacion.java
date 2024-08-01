package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Imputacion implements Serializable{
	private Integer numPreliquidacion;
	private String fechaPreliquidacion;
	private String codMoneda;
	private Double monto;
	private String debitoCredito;
	private String descripMoneda;
	private Integer codMedioPago;
	private String descMedioPago;
	private String codOrigenPago;
	private String descOrigenPago;
	
	public Integer getNumPreliquidacion() {
		return numPreliquidacion;
	}
	public void setNumPreliquidacion(Integer numPreliquidacion) {
		this.numPreliquidacion = numPreliquidacion;
	}
	public String getFechaPreliquidacion() {
		return fechaPreliquidacion;
	}
	public void setFechaPreliquidacion(String fechaPreliquidacion) {
		this.fechaPreliquidacion = fechaPreliquidacion;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getDebitoCredito() {
		return debitoCredito;
	}
	public void setDebitoCredito(String debitoCredito) {
		this.debitoCredito = debitoCredito;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public Integer getCodMedioPago() {
		return codMedioPago;
	}
	public void setCodMedioPago(Integer codMedioPago) {
		this.codMedioPago = codMedioPago;
	}
	public String getDescMedioPago() {
		return descMedioPago;
	}
	public void setDescMedioPago(String descMedioPago) {
		this.descMedioPago = descMedioPago;
	}
	public String getCodOrigenPago() {
		return codOrigenPago;
	}
	public void setCodOrigenPago(String codOrigenPago) {
		this.codOrigenPago = codOrigenPago;
	}
	public String getDescOrigenPago() {
		return descOrigenPago;
	}
	public void setDescOrigenPago(String descOrigenPago) {
		this.descOrigenPago = descOrigenPago;
	}
	
	
}
