package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class DatosBanco implements Serializable{
	private Integer codBanco;
	private String descripcionBanco;
	private String tipoTarjeta;
	private String descripOrigenTarjeta;
	private String numCuenta;
	private Integer numDomicilio;
	private String fechaVencimiento;
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Integer getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(Integer codBanco) {
		this.codBanco = codBanco;
	}
	public String getDescripcionBanco() {
		return descripcionBanco;
	}
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getDescripOrigenTarjeta() {
		return descripOrigenTarjeta;
	}
	public void setDescripOrigenTarjeta(String descripOrigenTarjeta) {
		this.descripOrigenTarjeta = descripOrigenTarjeta;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public Integer getNumDomicilio() {
		return numDomicilio;
	}
	public void setNumDomicilio(Integer numDomicilio) {
		this.numDomicilio = numDomicilio;
	}
	
	public String getDatos(){
		StringBuilder sb = new StringBuilder();
		sb.append(descripcionBanco);
		sb.append(" - ");
		sb.append(numCuenta);
		return sb.toString();
	}

}
