package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAltaTextoCotizacion extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String codTexto;
	private String impresion;
	private String fechaDesde;
	private String fechaHasta;
	private String detalleTexto;
	private String persiste;
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCodTexto() {
		return codTexto;
	}
	public void setCodTexto(String codTexto) {
		this.codTexto = codTexto;
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
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getDetalleTexto() {
		return detalleTexto;
	}
	public void setDetalleTexto(String detalleTexto) {
		this.detalleTexto = detalleTexto;
	}
	public String getPersiste() {
		return persiste;
	}
	public void setPersiste(String persiste) {
		this.persiste = persiste;
	}
	
}
