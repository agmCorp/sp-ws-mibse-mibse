package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarCertificadoCeroVehiculo extends ParamGenerico{

	private static final long serialVersionUID = 6989954652934333644L;
	private Integer numCotizacion;
	private String codMoneda;
	private String formaPago;
	private String codPromocion;
	private String tipoCalculo;
	private String fechaDesde;
	private String fechaHasta;
	private String codProductor;
	
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getCodPromocion() {
		return codPromocion;
	}
	public void setCodPromocion(String codPromocion) {
		this.codPromocion = codPromocion;
	}
	public String getTipoCalculo() {
		return tipoCalculo;
	}
	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
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
	

}
