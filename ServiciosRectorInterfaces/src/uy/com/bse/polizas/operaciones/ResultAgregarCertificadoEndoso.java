package uy.com.bse.polizas.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAgregarCertificadoEndoso extends ResultGenerico{
	
	private ArrayList<BienCert> bienes = new ArrayList<BienCert>();
	private String permiteAnular;
	private Integer numCotizacion;
	private String activo;
	private String codPlanCobertura;
	private String descPlanCobertura;
	private String habilitoInsertar;
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();
	
	
	public ArrayList<BienCert> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<BienCert> bienes) {
		this.bienes = bienes;
	}
	public String getPermiteAnular() {
		return permiteAnular;
	}
	public void setPermiteAnular(String permiteAnular) {
		this.permiteAnular = permiteAnular;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
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
	
	public void setUnBienCert(BienCert item){
		this.bienes.add(item);
	}
	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}
	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
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
}
