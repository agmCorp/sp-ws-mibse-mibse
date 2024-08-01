package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class AcreedorBien implements Serializable{
	private Integer codAcreedor;
	private String descripAcreedor;
	private Integer codObjeto;
	private String descripObjeto;
	private String codTipoAcreedor;
	private String descripTipoAcreedor;
	private Double porcentajeParticipacion;
	private String identificador;
	private String fechaExclusion;
	private String fechaInclusion;
	
	public Integer getCodAcreedor() {
		return codAcreedor;
	}
	public void setCodAcreedor(Integer codAcreedor) {
		this.codAcreedor = codAcreedor;
	}
	public String getDescripAcreedor() {
		return descripAcreedor;
	}
	public void setDescripAcreedor(String descripAcreedor) {
		this.descripAcreedor = descripAcreedor;
	}
	public Integer getCodObjeto() {
		return codObjeto;
	}
	public void setCodObjeto(Integer codObjeto) {
		this.codObjeto = codObjeto;
	}
	public String getDescripObjeto() {
		return descripObjeto;
	}
	public void setDescripObjeto(String descripObjeto) {
		this.descripObjeto = descripObjeto;
	}
	public String getCodTipoAcreedor() {
		return codTipoAcreedor;
	}
	public void setCodTipoAcreedor(String codTipoAcreedor) {
		this.codTipoAcreedor = codTipoAcreedor;
	}
	public String getDescripTipoAcreedor() {
		return descripTipoAcreedor;
	}
	public void setDescripTipoAcreedor(String descripTipoAcreedor) {
		this.descripTipoAcreedor = descripTipoAcreedor;
	}
	public Double getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}
	public void setPorcentajeParticipacion(Double porcentajeParticipacion) {
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
	public String getFechaInclusion() {
		return fechaInclusion;
	}
	public void setFechaInclusion(String fechaInclusion) {
		this.fechaInclusion = fechaInclusion;
	}

}
