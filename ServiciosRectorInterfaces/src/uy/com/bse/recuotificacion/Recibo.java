package uy.com.bse.recuotificacion;

import java.io.Serializable;

public class Recibo implements Serializable{
	private String fechaHasta;
	private Integer codEstado;
	private String descripEstado;
	private Double montoPremio;
	
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Integer getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}
	public String getDescripEstado() {
		return descripEstado;
	}
	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	public Double getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(Double montoPremio) {
		this.montoPremio = montoPremio;
	}
}
