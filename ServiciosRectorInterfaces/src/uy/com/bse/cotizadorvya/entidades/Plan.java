package uy.com.bse.cotizadorvya.entidades;

import java.io.Serializable;

public class Plan implements Serializable {
	private static final long serialVersionUID = 2585982842476433875L;

    private String codProducto;
	private String codPlan;
	private String nombrePlan;
	private Double mensual;
	private Double trimestral;
	private Double semestral;
	private Double anual;
	private Boolean muerteAccidental;
	private Boolean invalidez;
	private Boolean ingresoSeguro;
	private String textoError;
	private Integer indicadorReferencia;
	
	public String getCodProducto() {
		return codProducto;
	}
	
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	
	public String getCodPlan() {
		return codPlan;
	}
	
	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}
	
	public String getNombrePlan() {
		return nombrePlan;
	}
	
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
	
	public Double getMensual() {
		return mensual;
	}
	
	public void setMensual(Double mensual) {
		this.mensual = mensual;
	}
	
	public Double getTrimestral() {
		return trimestral;
	}
	
	public void setTrimestral(Double trimestral) {
		this.trimestral = trimestral;
	}
	
	public Double getSemestral() {
		return semestral;
	}
	
	public void setSemestral(Double semestral) {
		this.semestral = semestral;
	}
	
	public Double getAnual() {
		return anual;
	}
	
	public void setAnual(Double anual) {
		this.anual = anual;
	}
	
	public Boolean getMuerteAccidental() {
		return muerteAccidental;
	}
	
	public void setMuerteAccidental(Boolean muerteAccidental) {
		this.muerteAccidental = muerteAccidental;
	}
	
	public Boolean getInvalidez() {
		return invalidez;
	}
	
	public void setInvalidez(Boolean invalidez) {
		this.invalidez = invalidez;
	}
	
	public Boolean getIngresoSeguro() {
		return ingresoSeguro;
	}
	
	public void setIngresoSeguro(Boolean ingresoSeguro) {
		this.ingresoSeguro = ingresoSeguro;
	}
	
	public String getTextoError() {
		return textoError;
	}
	
	public void setTextoError(String textoError) {
		this.textoError = textoError;
	}
	
	public Integer getIndicadorReferencia() {
		return indicadorReferencia;
	}
	
	public void setIndicadorReferencia(Integer indicadorReferencia) {
		this.indicadorReferencia = indicadorReferencia;
	}
}
