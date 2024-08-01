package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class DetalleCuota implements Serializable{
	
	private Integer numCuota;
	private String fechaCuota;
	private Double montoCuota;
	
	public Integer getNumCuota() {
		return numCuota;
	}
	public void setNumCuota(Integer numCuota) {
		this.numCuota = numCuota;
	}
	public String getFechaCuota() {
		return fechaCuota;
	}
	public void setFechaCuota(String fechaCuota) {
		this.fechaCuota = fechaCuota;
	}
	public Double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}	

}
