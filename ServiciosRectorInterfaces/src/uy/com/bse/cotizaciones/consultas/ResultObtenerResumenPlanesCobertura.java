package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Componente;
import uy.com.bse.cotizaciones.entidades.Cumulo;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.Riesgo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerResumenPlanesCobertura extends ResultGenerico {
	
	private ArrayList<Componente> componentes = new ArrayList<Componente>();
	private ArrayList<FueraPauta> fueraPautas = new ArrayList<FueraPauta>();
	private ArrayList<Cumulo> cumulos = new ArrayList<Cumulo>();
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	private ArrayList<Riesgo> riesgos = new ArrayList<Riesgo>();
	private Double premioEmision;
	private Double premioFacturacion;
	
	public ArrayList<Componente> getComponentes() {
		return componentes;
	}
	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}
	public ArrayList<FueraPauta> getFueraPautas() {
		return fueraPautas;
	}
	public void setFueraPautas(ArrayList<FueraPauta> fueraPautas) {
		this.fueraPautas = fueraPautas;
	}
	public ArrayList<Cumulo> getCumulos() {
		return cumulos;
	}
	public void setCumulos(ArrayList<Cumulo> cumulos) {
		this.cumulos = cumulos;
	}
	
	public void setUnComponente(Componente item){
		this.componentes.add(item);
	}
	
	public void setUnaFueraPauta(FueraPauta item){
		this.fueraPautas.add(item);
	}
	
	public void setUnCumulo(Cumulo item){
		this.cumulos.add(item);
	}
	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}
	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}

	public void setUnCertificado(Certificado item){
		this.certificados.add(item);
	}
	public Double getPremioEmision() {
		return premioEmision;
	}
	public void setPremioEmision(Double premioEmision) {
		this.premioEmision = premioEmision;
	}
	
	public Double getPremioFacturacion() {
		return premioFacturacion;
	}
	public void setPremioFacturacion(Double premioFacturacion) {
		this.premioFacturacion = premioFacturacion;
	}
	
	public void setUnRiesgo(Riesgo item){
		this.riesgos.add(item);
	}
	public ArrayList<Riesgo> getRiesgos() {
		return riesgos;
	}
	public void setRiesgos(ArrayList<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}
	
}
