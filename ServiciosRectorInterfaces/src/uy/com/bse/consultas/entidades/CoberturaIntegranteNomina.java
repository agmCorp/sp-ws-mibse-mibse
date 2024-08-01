package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class CoberturaIntegranteNomina implements Serializable {

	private static final long serialVersionUID = 2064255036771036762L;
	
	private Integer codRamoContable;
	private String codCobertura;
	private String descCobertura;
	private Double montoSumaAsegurada;
	private String fechaInclusion;
	private String fechaExclusion;
	private Double tasa;
	private Double prima;
	private Double premio;
	private Double premioFacturacion;
	private String opcional;
	public Integer getCodRamoContable() {
		return codRamoContable;
	}
	public void setCodRamoContable(Integer codRamoContable) {
		this.codRamoContable = codRamoContable;
	}
	public String getCodCobertura() {
		return codCobertura;
	}
	public void setCodCobertura(String codCobertura) {
		this.codCobertura = codCobertura;
	}
	public String getDescCobertura() {
		return descCobertura;
	}
	public void setDescCobertura(String descCobertura) {
		this.descCobertura = descCobertura;
	}
	public Double getMontoSumaAsegurada() {
		return montoSumaAsegurada;
	}
	public void setMontoSumaAsegurada(Double montoSumaAsegurada) {
		this.montoSumaAsegurada = montoSumaAsegurada;
	}
	public String getFechaInclusion() {
		return fechaInclusion;
	}
	public void setFechaInclusion(String fechaInclusion) {
		this.fechaInclusion = fechaInclusion;
	}
	public Double getTasa() {
		return tasa;
	}
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	public Double getPrima() {
		return prima;
	}
	public void setPrima(Double prima) {
		this.prima = prima;
	}
	public Double getPremio() {
		return premio;
	}
	public void setPremio(Double premio) {
		this.premio = premio;
	}
	public Double getPremioFacturacion() {
		return premioFacturacion;
	}
	public void setPremioFacturacion(Double premioFacturacion) {
		this.premioFacturacion = premioFacturacion;
	}
	public String getOpcional() {
		return opcional;
	}
	public void setOpcional(String opcional) {
		this.opcional = opcional;
	}
	public String getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(String fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}
	
	


}
