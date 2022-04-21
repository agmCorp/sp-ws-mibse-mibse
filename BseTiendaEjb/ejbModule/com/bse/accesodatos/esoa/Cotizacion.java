package com.bse.accesodatos.esoa;

import java.util.ArrayList;
import java.util.Date;

public class Cotizacion{

private Ramo ramo;
private Producto producto;
private int sucursal;
private int nroCotizacion;
private String tipoDocumento;
private String nroDocumento;
private int productor;
private Moneda moneda;
private double premio;
private double premioFacturar;
private Date fechaDesde;
private Date fechaHasta;
private PlanPago planPago;
private PlanCobertura planCobertura;
private ArrayList<CuotaPago> cuotas;


public Cotizacion() {
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

public int getNroCotizacion() {
	return nroCotizacion;
}

public void setNroCotizacion(int nroCotizacion) {
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

public double getPremio() {
	return premio;
}

public void setPremio(double premio) {
	this.premio = premio;
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

public PlanPago getPlanPago() {
	return planPago;
}

public void setPlanPago(PlanPago planPago) {
	this.planPago = planPago;
}

public void setPremioFacturar(double premioFacturar) {
	this.premioFacturar=premioFacturar;
}

public double getPremioFacturar(){
	return premioFacturar;
}

public ArrayList<CuotaPago> getCuotas() {
	return cuotas;
}

public void setCuotas(ArrayList<CuotaPago> cuotas) {
	this.cuotas = cuotas;
}

public Moneda getMoneda() {
	return moneda;
}

public void setMoneda(Moneda moneda) {
	this.moneda = moneda;
}

public PlanCobertura getPlanCobertura() {
	return planCobertura;
}

public void setPlanCobertura(PlanCobertura planCobertura) {
	this.planCobertura = planCobertura;
}

}
