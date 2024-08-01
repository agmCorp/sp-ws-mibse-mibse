package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDetalleCuota extends ParamGenerico{
	
	private Integer numCotizacion;	
	private Integer numCertificado;
	private String codPlanPago;
	private Integer diaVto;
	private Double montoPrima;
	private Double montoPremio;
	
	
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
	public String getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(String codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
	public Integer getDiaVto() {
		return diaVto;
	}
	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}
	public Double getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(Double montoPrima) {
		this.montoPrima = montoPrima;
	}
	public Double getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(Double montoPremio) {
		this.montoPremio = montoPremio;
	}
	
	

}
