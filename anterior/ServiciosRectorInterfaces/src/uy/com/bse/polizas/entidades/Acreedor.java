package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Acreedor implements Serializable{
	private Integer codAcreedor;
	private String descripAcreedor;
	private String codTipoAcreedor;
	private String descripTipoAcreedor;
	private Integer codObjeto;
	private String descripObjeto;
	private Integer porcentajeParticipacion;
	private String fechaExclusion;
	
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
	public Integer getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}
	public void setPorcentajeParticipacion(Integer porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}
	public String getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(String fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}

}
