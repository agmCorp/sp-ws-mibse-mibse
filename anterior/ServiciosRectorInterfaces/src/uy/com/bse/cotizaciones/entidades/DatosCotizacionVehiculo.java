package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class DatosCotizacionVehiculo implements Serializable {

	private static final long serialVersionUID = 3467253966337842053L;
	private Integer numCotizacion;	
	private Integer numCertificado;
	private Integer codMoneda;
	private String codPromocion;
	private String codModoCalculo;
	private String codVigencia;
	private String fechaDesde;
	private String fechaHasta;
	private Integer numSucursal;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
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
	public String getCodPromocion() {
		return codPromocion;
	}
	public void setCodPromocion(String codPromocion) {
		this.codPromocion = codPromocion;
	}
	public String getCodModoCalculo() {
		return codModoCalculo;
	}
	public void setCodModoCalculo(String codModoCalculo) {
		this.codModoCalculo = codModoCalculo;
	}
	public String getCodVigencia() {
		return codVigencia;
	}
	public void setCodVigencia(String codVigencia) {
		this.codVigencia = codVigencia;
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
	public Integer getNumSucursal() {
		return numSucursal;
	}
	public void setNumSucursal(Integer numSucursal) {
		this.numSucursal = numSucursal;
	} 

}
