package com.bse.accesodatos.esoa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CART_POLIZAS_PORTAL")

public class PolizaPortal {

public static int ESTADO_POLIZA_ANULADA = 0; 
public static int ESTADO_POLIZA_COTIZADA = 1; 
public static int ESTADO_POLIZA_EMITIDA = 2; 
public static int ESTADO_POLIZA_FACTURADA = 3; 
public static int ESTADO_POLIZA_PAGADA = 4; 
public static int ESTADO_POLIZA_FINALIZADA = 5; 
public static int ESTADO_POLIZA_CON_ERROR = 6; 

@EmbeddedId
protected PolizaPortalPK polizaPortalPK;

@Temporal(TemporalType.DATE)
@Column(name = "CPP_FE_COTIZACION")
private Date fechaCotizacion;

@Column(name = "CPP_NU_POLIZA")
private int nroPoliza;

@Temporal(TemporalType.DATE)
@Column(name = "CPP_FE_EMISION")
private Date fechaEmision;

@Temporal(TemporalType.DATE)
@Column(name = "CPP_FECHA_DESDE")
private Date fechaDesde;

@Temporal(TemporalType.DATE)
@Column(name = "CPP_FECHA_HASTA")
private Date fechaHasta;

@Column(name = "CPP_ESTADO")
private int estado;

@Column(name = "CPP_NOTAS")
private String notas;

@Temporal(TemporalType.DATE)
@Column(name = "CPP_FECHA_MOD")
private Date fechaMod;

@Column(name = "CPP_PAGO_REDES")
private String pagoRedes;


public PolizaPortalPK getPolizaPortalPK() {
	return polizaPortalPK;
}

public void setPolizaPortalPK(PolizaPortalPK polizaPortalPK) {
	this.polizaPortalPK = polizaPortalPK;
}

public Date getFechaCotizacion() {
	return fechaCotizacion;
}

public void setFechaCotizacion(Date fechaCotizacion) {
	this.fechaCotizacion = fechaCotizacion;
}

public int getNroPoliza() {
	return nroPoliza;
}

public void setNroPoliza(int nroPoliza) {
	this.nroPoliza = nroPoliza;
}

public Date getFechaEmision() {
	return fechaEmision;
}

public void setFechaEmision(Date fechaEmision) {
	this.fechaEmision = fechaEmision;
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

public int getEstado() {
	return estado;
}

public void setEstado(int estado) {
	this.estado = estado;
}

public String getNotas() {
	return notas;
}

public void setNotas(String notas) {
	this.notas = notas;
}

public Date getFechaMod() {
	return fechaMod;
}

public void setFechaMod(Date fechaMod) {
	this.fechaMod = fechaMod;
}

public String getPagoRedes() {
	return pagoRedes;
}

public void setPagoRedes(String pagoRedes) {
	this.pagoRedes = pagoRedes;
}
	
	
}
