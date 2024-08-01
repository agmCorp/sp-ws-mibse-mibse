package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteADTBPS implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numPoliza;
	private String razonSocial;
	private String cedula;
	private String fechaDesde;
	private String actividad;
	private Double tasa;

	public ReporteADTBPS() {
		super();
	}

	public ReporteADTBPS(Integer numPoliza, String razonSocial, String cedula, String actividad, Double tasa, String fechaDesde) {
		super();
		this.numPoliza = numPoliza;

		this.razonSocial = razonSocial;
		this.cedula = cedula;
		this.actividad = actividad;
		this.tasa = tasa;
		this.fechaDesde = fechaDesde;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCedula() {
		return cedula;
	}

	
	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

}
