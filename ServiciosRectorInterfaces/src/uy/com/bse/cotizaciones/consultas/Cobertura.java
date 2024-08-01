package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.operaciones.PlanPago;

public class Cobertura implements Serializable{
	private Integer ramoCod;
	private String coberturaCod;
	private String coberturaDesc;
	private Integer elementosNum;
	private Integer bienNum;
	private String bienDesc;
	private String sumaAsegurada;
	private String tasaPrima;
	private Double prima;
	private String nombre;
	private String descripcion;
	private Double capital;
	private String editable;
	private Double valor;
	private String actualizado;
	
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	//a revisar 
	private Boolean errorGenerado;
	private String error;
	private String requerido;

	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	private ArrayList <PlanPago> planes;
	
	
	public Integer getRamoCod() {
		return ramoCod;
	}
	public void setRamoCod(Integer ramoCod) {
		this.ramoCod = ramoCod;
	}
	
	public String getCoberturaCod() {
		return coberturaCod;
	}
	public void setCoberturaCod(String coberturaCod) {
		this.coberturaCod = coberturaCod;
	}
	public String getCoberturaDesc() {
		return coberturaDesc;
	}
	public void setCoberturaDesc(String coberturaDesc) {
		this.coberturaDesc = coberturaDesc;
	}
	public Integer getElementosNum() {
		return elementosNum;
	}
	public void setElementosNum(Integer elementosNum) {
		this.elementosNum = elementosNum;
	}
	public Integer getBienNum() {
		return bienNum;
	}
	public void setBienNum(Integer bienNum) {
		this.bienNum = bienNum;
	}
	public String getBienDesc() {
		return bienDesc;
	}
	public void setBienDesc(String bienDesc) {
		this.bienDesc = bienDesc;
	}
	public String getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(String sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public String getTasaPrima() {
		return tasaPrima;
	}
	public void setTasaPrima(String tasaPrima) {
		this.tasaPrima = tasaPrima;
	}
	public Double getPrima() {
		return prima;
	}
	public void setPrima(Double prima) {
		this.prima = prima;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getErrorGenerado() {
		return errorGenerado;
	}
	public void setErrorGenerado(Boolean errorGenerado) {
		this.errorGenerado = errorGenerado;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public ArrayList<PlanPago> getPlanes() {
		return planes;
	}
	public void setPlanes(ArrayList<PlanPago> planes) {
		this.planes = planes;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getActualizado() {
		return actualizado;
	}
	public void setActualizado(String actualizado) {
		this.actualizado = actualizado;
	}
	
	
		
}