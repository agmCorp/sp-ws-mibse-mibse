package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCoberturasXCertificado extends ResultGenerico{
	
	private String codPlanCobertura;
	private String descPlanCobertura;
	private String codPlanPago;
	private Integer diaVto;
	private Integer codNivel;
	private String descNivel;
	private String permiteAnular;
	private String activo;
	private String habilitoInsertar;
	private String existeAsegurado;
	
	private ArrayList<PlanCobertura> planesCobertura = new ArrayList<PlanCobertura>();
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
	public String getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(String codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
	public Integer getDiaVto() {
		return diaVto;
	}
	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}
	public Integer getCodNivel() {
		return codNivel;
	}
	public void setCodNivel(Integer codNivel) {
		this.codNivel = codNivel;
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
	public ArrayList<PlanCobertura> getPlanesCobertura() {
		return planesCobertura;
	}
	public void setPlanesCobertura(ArrayList<PlanCobertura> planesCobertura) {
		this.planesCobertura = planesCobertura;
	}
	
	public ArrayList<Bien> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<Bien> bienes) {
		this.bienes = bienes;
	}


}
