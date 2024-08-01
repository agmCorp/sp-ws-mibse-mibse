package uy.com.bse.recuotificacion;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Imputacion;

public class Consolidado implements Serializable{
	private Integer codRamo;
	private Integer numPoliza;
	private Integer numCertificado;
	private Integer codMoneda;
	private String descripMoneda;
	private String nombreCliente;
	private Double montoTotalRecibos;
	private Double montoTotalImputaciones;
	private Double montoSaldo;
	private Double montoComisiones;
	private ArrayList<Recibo> recibos = new ArrayList<Recibo>();
	private ArrayList<Imputacion> imputaciones = new ArrayList<Imputacion>();
	
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Double getMontoTotalRecibos() {
		return montoTotalRecibos;
	}
	public void setMontoTotalRecibos(Double montoTotalRecibos) {
		this.montoTotalRecibos = montoTotalRecibos;
	}
	public Double getMontoTotalImputaciones() {
		return montoTotalImputaciones;
	}
	public void setMontoTotalImputaciones(Double montoTotalImputaciones) {
		this.montoTotalImputaciones = montoTotalImputaciones;
	}
	public Double getMontoSaldo() {
		return montoSaldo;
	}
	public void setMontoSaldo(Double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}
	public Double getMontoComisiones() {
		return montoComisiones;
	}
	public void setMontoComisiones(Double montoComisiones) {
		this.montoComisiones = montoComisiones;
	}
	public ArrayList<Recibo> getRecibos() {
		return recibos;
	}
	public void setRecibos(ArrayList<Recibo> recibos) {
		this.recibos = recibos;
	}
	public ArrayList<Imputacion> getImputaciones() {
		return imputaciones;
	}
	public void setImputaciones(ArrayList<Imputacion> imputaciones) {
		this.imputaciones = imputaciones;
	}
	
	public void setUnRecibo(Recibo item){
		this.recibos.add(item);
	}
	
	public void setUnaImputacion(Imputacion item){
		this.imputaciones.add(item);
	}
}
