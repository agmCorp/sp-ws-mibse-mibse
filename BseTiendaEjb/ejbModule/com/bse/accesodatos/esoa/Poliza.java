package com.bse.accesodatos.esoa;

import java.util.ArrayList;
import java.util.Date;

public class Poliza{

private Ramo ramo;
private Producto producto;
private int sucursal;
private long nroCotizacion;
private String tipoDocumento;
private String nroDocumento;
private int productor;
private Moneda moneda;
private double premio;
private double premioFacturar;
private Date fechaDesde;
private Date fechaHasta;
private int nroPoliza;
private PlanPago planPago;
private PlanCobertura planCobertura;
private ArrayList<CuotaPago> cuotas;

public Poliza() {
}

public Poliza(Ramo ramo, Producto producto, int sucursal, long nroCotizacion,
		String tipoDocumento, String nroDocumento, int productor,
		Moneda moneda, double premio, double premioFacturar, Date fechaDesde,
		Date fechaHasta, int nroPoliza, PlanPago planPago,
		PlanCobertura planCobertura, ArrayList<CuotaPago> cuotas) {
	super();
	this.ramo = ramo;
	this.producto = producto;
	this.sucursal = sucursal;
	this.nroCotizacion = nroCotizacion;
	this.tipoDocumento = tipoDocumento;
	this.nroDocumento = nroDocumento;
	this.productor = productor;
	this.moneda = moneda;
	this.premio = premio;
	this.premioFacturar = premioFacturar;
	this.fechaDesde = fechaDesde;
	this.fechaHasta = fechaHasta;
	this.nroPoliza = nroPoliza;
	this.planPago = planPago;
	this.planCobertura = planCobertura;
	this.cuotas = cuotas;
}

public Ramo getRamo() {
	return ramo;
}

public void setRamo(Ramo ramo) {
	this.ramo = ramo;
}

public Producto getProducto() {
	return producto;
}

public void setProducto(Producto producto) {
	this.producto = producto;
}

public int getSucursal() {
	return sucursal;
}

public void setSucursal(int sucursal) {
	this.sucursal = sucursal;
}

public long getNroCotizacion() {
	return nroCotizacion;
}

public void setNroCotizacion(long nroCotizacion) {
	this.nroCotizacion = nroCotizacion;
}

public String getTipoDocumento() {
	return tipoDocumento;
}

public void setTipoDocumento(String tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
}

public String getNroDocumento() {
	return nroDocumento;
}

public void setNroDocumento(String nroDocumento) {
	this.nroDocumento = nroDocumento;
}

public int getProductor() {
	return productor;
}

public void setProductor(int productor) {
	this.productor = productor;
}

public Moneda getMoneda() {
	return moneda;
}

public void setMoneda(Moneda moneda) {
	this.moneda = moneda;
}

public double getPremio() {
	return premio;
}

public void setPremio(double premio) {
	this.premio = premio;
}

public double getPremioFacturar() {
	return premioFacturar;
}

public void setPremioFacturar(double premioFacturar) {
	this.premioFacturar = premioFacturar;
}

public Date getFechaDesde() {
	return fechaDesde;
}

public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
}

public Date getFechaHasta() {
	return fechaHasta;
}

public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
}

public int getNroPoliza() {
	return nroPoliza;
}

public void setNroPoliza(int nroPoliza) {
	this.nroPoliza = nroPoliza;
}

public PlanPago getPlanPago() {
	return planPago;
}

public void setPlanPago(PlanPago planPago) {
	this.planPago = planPago;
}

public PlanCobertura getPlanCobertura() {
	return planCobertura;
}

public void setPlanCobertura(PlanCobertura planCobertura) {
	this.planCobertura = planCobertura;
}

public ArrayList<CuotaPago> getCuotas() {
	return cuotas;
}

public void setCuotas(ArrayList<CuotaPago> cuotas) {
	this.cuotas = cuotas;
}

}
