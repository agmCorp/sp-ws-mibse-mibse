package com.bse.accesodatos.epagos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Factura implements Comparable<Factura>, Serializable{

private Integer ramo;
private Integer poliza;
private Integer certificado;

private String tipoDocumento;
private String nroDocumento;

private String nombres;
private String apellidos;

private Long numeroFactura;
private Date fechaVto1;
private Date fechaVto2;
private Date fechaFactura;

private String moneda; // (UYU, USD, EUR)
private Double importePagar;
private Double importeGravado;

private String cb1;
private String cb2;

private String consumoFinal;
private String descripcionRamo;
private String facturaCFE;
private String producto;
private String descripcionProducto;
private String codigoAdhesion;
private String cuotas;
private String datosBien;
private Long docId;

private String pagable;
private String pagableObs;

private Date fechaVto3;


public Integer getRamo() {
	return ramo;
}

public void setRamo(Integer ramo) {
	this.ramo = ramo;
}

public Integer getPoliza() {
	return poliza;
}

public void setPoliza(Integer poliza) {
	this.poliza = poliza;
}

public Integer getCertificado() {
	return certificado;
}

public void setCertificado(Integer certificado) {
	this.certificado = certificado;
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

public String getNombres() {
	return nombres;
}

public void setNombres(String nombres) {
	this.nombres = nombres;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public Long getNumeroFactura() {
	return numeroFactura;
}

public void setNumeroFactura(Long numeroFactura) {
	this.numeroFactura = numeroFactura;
}

public Date getFechaVto1() {
	return fechaVto1;
}

public void setFechaVto1(Date fechaVto1) {
	this.fechaVto1 = fechaVto1;
}

public Date getFechaVto2() {
	return fechaVto2;
}

public void setFechaVto2(Date fechaVto2) {
	this.fechaVto2 = fechaVto2;
}

public Date getFechaFactura() {
	return fechaFactura;
}

public void setFechaFactura(Date fechaFactura) {
	this.fechaFactura = fechaFactura;
}

public String getMoneda() {
	return moneda;
}

public void setMoneda(String moneda) {
	this.moneda = moneda;
}

public Double getImportePagar() {
	return importePagar;
}

public void setImportePagar(Double importePagar) {
	this.importePagar = importePagar;
}

public Double getImporteGravado() {
	return importeGravado;
}

public void setImporteGravado(Double importeGravado) {
	this.importeGravado = importeGravado;
}

public String getCb1() {
	return cb1;
}

public void setCb1(String cb1) {
	this.cb1 = cb1;
}

public String getCb2() {
	return cb2;
}

public void setCb2(String cb2) {
	this.cb2 = cb2;
}

public String getConsumoFinal() {
	return consumoFinal;
}

public void setConsumoFinal(String consumoFinal) {
	this.consumoFinal = consumoFinal;
}

public void setDescripcionRamo(String descripcionRamo) {
	this.descripcionRamo = descripcionRamo;
}

public String getDescripcionRamo() {
	return descripcionRamo;
}

public void setFacturaCFE(String facturaCFE) {
	this.facturaCFE = facturaCFE;
}

public String getFacturaCFE() {
	return facturaCFE;
}

public void setProducto(String producto) {
	this.producto=producto;
}

public String getProducto() {
	return producto;
}

public void setDescripcionProducto(String descripcionProducto) {
	this.descripcionProducto=descripcionProducto;
}

public String getDescripcionProducto() {
	return descripcionProducto;
}

public void setCodigoAdhesion(String codigoAdhesion) {
	this.codigoAdhesion = codigoAdhesion;
}

public String getCodigoAdhesion() {
	return codigoAdhesion;
}

public void setCuotas(String cuotas) {
	this.cuotas=cuotas;
}

public String getCuotas() {
	return cuotas;
}

@Override
public int compareTo(Factura factura) {
	SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd"); 
	
	String clave1 = getRamo().toString()+"-"+getProducto()+"-"+getPoliza().toString()+"-"+dt.format(getFechaVto1()); 
	String clave2 = factura.getRamo().toString()+"-"+factura.getProducto()+"-"+factura.getPoliza().toString()+"-"+dt.format(factura.getFechaVto1());
	
	return clave1.compareTo(clave2);
}

public void setDatosBien(String datosBien) {
	this.datosBien=datosBien;
}

public String getDatosBien() {
	return datosBien;
}

public void setDocumentId(Long docId) {
	this.docId=docId;
}

public Long getDocumentId() {
	return docId;
}

public String getPagable() {
	return pagable;
}

public void setPagable(String pagable) {
	this.pagable = pagable;
}

public String getPagableObs() {
	return pagableObs;
}

public void setPagableObs(String pagableObs) {
	this.pagableObs = pagableObs;
}

@XmlTransient
public Date getFechaVto3() {
	return fechaVto3;
}

public void setFechaVto3(Date fechaVto3) {
	this.fechaVto3 = fechaVto3;
}

}
