package uy.com.bse.polizas.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.recuotificacion.DatosCertificado;

public class DatosParticularesEndoso implements Serializable{
	private Integer codRamo;
	private String descRamo;
	private Integer numPoliza;
	private Integer numEndoso;
	private Integer codCliente;
	private String descCliente;
	private String fechaDesde;
	private String fechaHasta;
	private Integer codMedioPago; 
	private String descMedioPago; 
	private String codOrigen;
	private String descOrigen;
	private Integer codOrigenEndoso;
	private String descOrigenEndoso;
	
	private ArrayList<DatosCertificado> certificados = new ArrayList<DatosCertificado>();
	
	
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getDescRamo() {
		return descRamo;
	}
	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	public String getDescCliente() {
		return descCliente;
	}
	public void setDescCliente(String descCliente) {
		this.descCliente = descCliente;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public String getDescOrigen() {
		return descOrigen;
	}
	public void setDescOrigen(String descripOrigen) {
		this.descOrigen = descripOrigen;
	}
	public Integer getCodOrigenEndoso() {
		return codOrigenEndoso;
	}
	public void setCodOrigenEndoso(Integer codOrigenEndoso) {
		this.codOrigenEndoso = codOrigenEndoso;
	}
	public String getDescOrigenEndoso() {
		return descOrigenEndoso;
	}
	public void setDescOrigenEndoso(String descOrigenEndoso) {
		this.descOrigenEndoso = descOrigenEndoso;
	}
	public ArrayList<DatosCertificado> getCertificados() {
		return certificados;
	}
	public void setCertificados(ArrayList<DatosCertificado> certificados) {
		this.certificados = certificados;
	}
	
	public void setUnDatoCertificado(DatosCertificado datos){
		this.certificados.add(datos);
	}
	
}
