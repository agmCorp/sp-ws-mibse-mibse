package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamRecuotificarPoliza extends ParamGenerico{
	private String codRamo;
	private String numPoliza;
	private String numCertificado;
	private String numEndoso;
	private String numCotizacion;
	private String fechaEmision;
	private String codPlanPago;
	private String diaVencimiento;
	private String montoPrima;
	private String montoPremio;
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(String numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(String codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
	public String getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(String diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public String getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(String montoPrima) {
		this.montoPrima = montoPrima;
	}
	public String getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(String montoPremio) {
		this.montoPremio = montoPremio;
	}	
	
}
