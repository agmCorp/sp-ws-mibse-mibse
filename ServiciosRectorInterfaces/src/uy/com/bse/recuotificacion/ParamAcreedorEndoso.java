package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAcreedorEndoso extends ParamGenerico{
	private static final long serialVersionUID = 1L;
	private Integer codAcreedor;
	private Integer codObjeto;
	private Integer numPoliza;
	private Integer codRamo;
	private Integer numCertificado;
	private Integer numEndoso;
	private String codTipoAcreedor;
	private Double porcentajeParticipacion;
	private String fechaExclusion;
	private Integer consecutivoBien;
	private String identificador;
	public Integer getCodAcreedor() {
		return codAcreedor;
	}
	public void setCodAcreedor(Integer codAcreedor) {
		this.codAcreedor = codAcreedor;
	}
	public Integer getCodObjeto() {
		return codObjeto;
	}
	public void setCodObjeto(Integer codObjeto) {
		this.codObjeto = codObjeto;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getCodTipoAcreedor() {
		return codTipoAcreedor;
	}
	public void setCodTipoAcreedor(String codTipoAcreedor) {
		this.codTipoAcreedor = codTipoAcreedor;
	}
	public Double getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}
	public void setPorcentajeParticipacion(Double porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}
	public String getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(String fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}
	public Integer getConsecutivoBien() {
		return consecutivoBien;
	}
	public void setConsecutivoBien(Integer consecutivoBien) {
		this.consecutivoBien = consecutivoBien;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}
