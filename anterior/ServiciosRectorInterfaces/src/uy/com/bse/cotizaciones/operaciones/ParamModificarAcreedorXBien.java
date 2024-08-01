package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamModificarAcreedorXBien extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String numConsecutivoBien;
	private String codAcreedor;
	private String codObjeto;
	private String tipoAcreedor;
	private String porcentajeParticipacion;
	private String identificador;
	private String fechaExclusion;
	
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
	public String getNumConsecutivoBien() {
		return numConsecutivoBien;
	}
	public void setNumConsecutivoBien(String numConsecutivoBien) {
		this.numConsecutivoBien = numConsecutivoBien;
	}
	public String getCodAcreedor() {
		return codAcreedor;
	}
	public void setCodAcreedor(String codAcreedor) {
		this.codAcreedor = codAcreedor;
	}
	public String getCodObjeto() {
		return codObjeto;
	}
	public void setCodObjeto(String codObjeto) {
		this.codObjeto = codObjeto;
	}
	public String getTipoAcreedor() {
		return tipoAcreedor;
	}
	public void setTipoAcreedor(String tipoAcreedor) {
		this.tipoAcreedor = tipoAcreedor;
	}
	public String getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}
	public void setPorcentajeParticipacion(String porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(String fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}
	
}
