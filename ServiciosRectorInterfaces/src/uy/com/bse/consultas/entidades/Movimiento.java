package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class Movimiento implements Serializable {
	
	
	private static final long serialVersionUID = -9127033078618914937L;
	
	private String fechaMovimiento;
	private String tipoMovimiento;
	private String descMovimiento;
	private Integer codMoneda;
	private String descMoneda;
	private String codRamo;
	private String descRamo;
	private String numPoliza;
	private String descAsegurado;
	private Double montoMovimiento;
	private String codDebCred;
	private String descObservaciones;
	
	
	
	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getDescMovimiento() {
		return descMovimiento;
	}
	public void setDescMovimiento(String descMovimiento) {
		this.descMovimiento = descMovimiento;
	}
	public Integer getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescMoneda() {
		return descMoneda;
	}
	
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getDescAsegurado() {
		return descAsegurado;
	}
	public void setDescAsegurado(String descAsegurado) {
		this.descAsegurado = descAsegurado;
	}
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getDescRamo() {
		return descRamo;
	}
	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}
	public Double getMontoMovimiento() {
		return montoMovimiento;
	}
	public void setMontoMovimiento(Double montoMovimiento) {
		this.montoMovimiento = montoMovimiento;
	}
	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}
	public String getCodDebCred() {
		return codDebCred;
	}
	public void setCodDebCred(String codDebCred) {
		this.codDebCred = codDebCred;
	}
	public String getDescObservaciones() {
		return descObservaciones;
	}
	public void setDescObservaciones(String descObservaciones) {
		this.descObservaciones = descObservaciones;
	}
 
	

}
