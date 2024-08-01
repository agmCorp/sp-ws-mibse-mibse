package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class Plan implements Serializable{
	private String planCod;
	private String planDesc;
	private Double premio; 
	private String seleccion;
	
	public String getPlanCod() {
		return planCod;
	}
	public void setPlanCod(String planCod) {
		this.planCod = planCod;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public Double getPremio() {
		return premio;
	}
	public void setPremio(Double premio) {
		this.premio = premio;
	}
	public String getSeleccion() {
		return seleccion;
	}
	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}
}
