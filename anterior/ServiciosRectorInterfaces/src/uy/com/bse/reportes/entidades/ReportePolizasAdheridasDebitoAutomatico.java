package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReportePolizasAdheridasDebitoAutomatico implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numPoliza;
	private Integer codRamo;
	private String fechaDesde;
	private String medioPago;
	private String origen;
	
	public ReportePolizasAdheridasDebitoAutomatico(Integer numPoliza, Integer codRamo, String fechaDesde, String medioPago, String origen) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.fechaDesde = fechaDesde;
		this.medioPago = medioPago;
		this.origen = origen;
	}
	public ReportePolizasAdheridasDebitoAutomatico() {
		super();
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
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
}
