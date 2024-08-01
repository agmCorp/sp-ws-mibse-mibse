package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultMontosIniciales extends ResultGenerico {
	private static final long serialVersionUID = 1490696541409618108L;
	
	private Double minCapital;
	private Double maxCapital;
	private Double minIngresoMensual;
	private Double maxIngresoMensual;
	
	public Double getMinCapital() {
		return minCapital;
	}
	
	public void setMinCapital(Double minCapital) {
		this.minCapital = minCapital;
	}
	
	public Double getMaxCapital() {
		return maxCapital;
	}
	
	public void setMaxCapital(Double maxCapital) {
		this.maxCapital = maxCapital;
	}
	
	public Double getMinIngresoMensual() {
		return minIngresoMensual;
	}
	
	public void setMinIngresoMensual(Double minIngresoMensual) {
		this.minIngresoMensual = minIngresoMensual;
	}
	
	public Double getMaxIngresoMensual() {
		return maxIngresoMensual;
	}
	
	public void setMaxIngresoMensual(Double maxIngresoMensual) {
		this.maxIngresoMensual = maxIngresoMensual;
	}
}
