package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Bien;

public class DatosValidos implements Serializable{
	
	private String errorValidacion;
	private ArrayList<Validacion> validaciones = new ArrayList<Validacion>();
	private ArrayList<PlanCobertura> planesCobertura = new ArrayList<PlanCobertura>();
	private Integer diaVencimiento;
	private String descripMoneda;
	private String simboloMoneda;	
	private ArrayList<Bien> bienes = new ArrayList<Bien>();
	
	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public String getSimboloMoneda() {
		return simboloMoneda;
	}
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}
	public String getErrorValidacion() {
		return errorValidacion;
	}
	public void setErrorValidacion(String errorValidacion) {
		this.errorValidacion = errorValidacion;
	}
	public ArrayList<Validacion> getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(ArrayList<Validacion> validaciones) {
		this.validaciones = validaciones;
	}
	public ArrayList<PlanCobertura> getPlanesCobertura() {
		return planesCobertura;
	}
	public void setPlanesCobertura(ArrayList<PlanCobertura> planesCobertura) {
		this.planesCobertura = planesCobertura;
	}
	public void setUnaValidacion(Validacion validacion){
		this.validaciones.add(validacion);
	}
	public void setUnPlanCobertura(PlanCobertura planCobertura){
		this.planesCobertura.add(planCobertura);	
	}
	public ArrayList<Bien> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<Bien> bienes) {
		this.bienes = bienes;
	}
	public void setUnBien(Bien bien){
		this.bienes.add(bien);
	}
}
