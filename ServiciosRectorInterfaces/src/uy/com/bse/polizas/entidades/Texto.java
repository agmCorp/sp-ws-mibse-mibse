package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Texto implements Serializable{
	private Integer numConsecutivo;
	private Integer numEndoso;
	private Integer codTexto;
	private String descripTexto;
	private String impresion;
	private String fechaDesde;
	private String persiste;
	private String darBaja;
	private String fechaHasta;
	private Integer codSucursal;
	
	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}
	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getCodTexto() {
		return codTexto;
	}
	public void setCodTexto(Integer codTexto) {
		this.codTexto = codTexto;
	}
	public String getDescripTexto() {
		return descripTexto;
	}
	public void setDescripTexto(String descripTexto) {
		this.descripTexto = descripTexto;
	}
	public String getImpresion() {
		return impresion;
	}
	public void setImpresion(String impresion) {
		this.impresion = impresion;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getPersiste() {
		return persiste;
	}
	public void setPersiste(String persiste) {
		this.persiste = persiste;
	}
	public String getDarBaja() {
		return darBaja;
	}
	public void setDarBaja(String darBaja) {
		this.darBaja = darBaja;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Integer getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}

}
