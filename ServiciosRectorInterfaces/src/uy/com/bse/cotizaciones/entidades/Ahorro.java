package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class Ahorro implements Serializable{
	private String codProceso;
	private String descripcionProceso;
	private Double montoPagar;
	private Double montoAporte;
	private Integer codPlanPago;
	private String descripcionPlanPago;
	private String fechaDesde;
	private String fechaHasta;
	
	public String getCodProceso() {
		return codProceso;
	}
	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}
	public String getDescripcionProceso() {
		return descripcionProceso;
	}
	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}
	public Double getMontoPagar() {
		return montoPagar;
	}
	public void setMontoPagar(Double montoPagar) {
		this.montoPagar = montoPagar;
	}
	public Double getMontoAporte() {
		return montoAporte;
	}
	public void setMontoAporte(Double montoAporte) {
		this.montoAporte = montoAporte;
	}
	public Integer getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(Integer codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
	public String getDescripcionPlanPago() {
		return descripcionPlanPago;
	}
	public void setDescripcionPlanPago(String descripcionPlanPago) {
		this.descripcionPlanPago = descripcionPlanPago;
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
	
}
