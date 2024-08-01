package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCoberturasXBien extends ResultGenerico{
	
	private String codPlanCobertura;
	private String descPlanCobertura;
	private String descNivel;
	private String permiteAnular;
	private String activo;
	private String habilitoInsertar;
	private String existeAsegurado;
	private String requiereAsegurado;
	private Integer numCertificado;
	
	
	private ArrayList<Bien> bienes = new ArrayList<Bien>();
	
	
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public String getDescPlanCobertura() {
		return descPlanCobertura;
	}
	public void setDescPlanCobertura(String descPlanCobertura) {
		this.descPlanCobertura = descPlanCobertura;
	}
	
	public String getDescNivel() {
		return descNivel;
	}
	public void setDescNivel(String descNivel) {
		this.descNivel = descNivel;
	}
	public String getPermiteAnular() {
		return permiteAnular;
	}
	public void setPermiteAnular(String permiteAnular) {
		this.permiteAnular = permiteAnular;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}
	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	public String getExisteAsegurado() {
		return existeAsegurado;
	}
	public void setExisteAsegurado(String existeAsegurado) {
		this.existeAsegurado = existeAsegurado;
	}
	 
	public ArrayList<Bien> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<Bien> bienes) {
		this.bienes = bienes;
	}
	public String getRequiereAsegurado() {
		return requiereAsegurado;
	}
	public void setRequiereAsegurado(String requiereAsegurado) {
		this.requiereAsegurado = requiereAsegurado;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}


}
