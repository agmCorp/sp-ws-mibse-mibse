package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class DatoPolizaFlotante implements Serializable {
	
	private static final long serialVersionUID = -4824863420551648891L;
	
	private String asegurado;
	private Integer numPoliza;
	private Integer identificadorWeb;
	private String fechaEmbarque;
	private String tieneSeccionDerechos;
	private String codTipoFacturacion;
	private String descTipoFacturacion;
	private String descEstado;
	private Integer codMoneda;
	private Double sumaAsegurada;
	private Double premioNeto;
	private Integer cantidadPolizas;
	private String identificadorEmbarque;
	private Integer polizaFactura;
	private String numDua;
	private String observaciones;
	private String codAsignado;
	
	
	public String getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	public String getTieneSeccionDerechos() {
		return tieneSeccionDerechos;
	}
	public void setTieneSeccionDerechos(String tieneSeccionDerechos) {
		this.tieneSeccionDerechos = tieneSeccionDerechos;
	}
	public String getCodTipoFacturacion() {
		return codTipoFacturacion;
	}
	public void setCodTipoFacturacion(String codTipoFacturacion) {
		this.codTipoFacturacion = codTipoFacturacion;
	}
	public String getDescEstado() {
		return descEstado;
	}
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	public Integer getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	public Double getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(Double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public Double getPremioNeto() {
		return premioNeto;
	}
	public void setPremioNeto(Double premioNeto) {
		this.premioNeto = premioNeto;
	}
	public Integer getCantidadPolizas() {
		return cantidadPolizas;
	}
	public void setCantidadPolizas(Integer cantidadPolizas) {
		this.cantidadPolizas = cantidadPolizas;
	}
	public Integer getIdentificadorWeb() {
		return identificadorWeb;
	}
	public void setIdentificadorWeb(Integer identificadorWeb) {
		this.identificadorWeb = identificadorWeb;
	}
	public String getIdentificadorEmbarque() {
		return identificadorEmbarque;
	}
	public void setIdentificadorEmbarque(String identificadorEmbarque) {
		this.identificadorEmbarque = identificadorEmbarque;
	}
	public Integer getPolizaFactura() {
		return polizaFactura;
	}
	public void setPolizaFactura(Integer polizaFactura) {
		this.polizaFactura = polizaFactura;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getNumDua() {
		return numDua;
	}
	public void setNumDua(String numDua) {
		this.numDua = numDua;
	}
	public String getCodAsignado() {
		return codAsignado;
	}
	public void setCodAsignado(String codAsignado) {
		this.codAsignado = codAsignado;
	}
	public String getDescTipoFacturacion() {
		return descTipoFacturacion;
	}
	public void setDescTipoFacturacion(String descTipoFacturacion) {
		this.descTipoFacturacion = descTipoFacturacion;
	}
	
	

}
