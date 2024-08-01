package uy.com.bse.polizas.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAnularCertificadoEndoso extends ResultGenerico{
	
	private String permiteAnular;
	private String activo;
	private String codPlanCobertura;
	private String descPlanCobertura;
	private String habilitoInsertar;
	private Double montoCuotaCert;
	private Double montoTotalCert;
	
	public Double getMontoCuotaCert() {
		return montoCuotaCert;
	}
	public void setMontoCuotaCert(Double montoCuotaCert) {
		this.montoCuotaCert = montoCuotaCert;
	}
	public Double getMontoTotalCert() {
		return montoTotalCert;
	}
	public void setMontoTotalCert(Double montoTotalCert) {
		this.montoTotalCert = montoTotalCert;
	}
	private ArrayList<Bien> bienes =  new ArrayList<Bien>();
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
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
	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}
	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	public ArrayList<Bien> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<Bien> bienes) {
		this.bienes = bienes;
	}
	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}
	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}
	
	


}
