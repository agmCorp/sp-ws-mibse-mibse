package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.utilitario.dato.ResultXmlPL;

public class ResultAgregarCertificado extends ResultXmlPL{
	private Integer numConsecutivo;
	private ArrayList<BienCert> bienes;
	private String existeAsegurado;
	private String permiteAnular;
	private String habilitoInsertar;
	private String requiereAsegurado;
	
	
	public ResultAgregarCertificado(){
		this.bienes = new ArrayList<BienCert>();
	}

	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}

	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}

	public ArrayList<BienCert> getBienes() {
		return bienes;
	}

	public void setBienes(ArrayList<BienCert> bienes) {
		this.bienes = bienes;
	}
	
	public void setUnBien(BienCert bien){
		this.bienes.add(bien);
	}

	public String getExisteAsegurado() {
		return existeAsegurado;
	}

	public void setExisteAsegurado(String existeAsegurado) {
		this.existeAsegurado = existeAsegurado;
	}

	public String getPermiteAnular() {
		return permiteAnular;
	}

	public void setPermiteAnular(String permiteAnular) {
		this.permiteAnular = permiteAnular;
	}

	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}

	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}

	public String getRequiereAsegurado() {
		return requiereAsegurado;
	}

	public void setRequiereAsegurado(String requiereAsegurado) {
		this.requiereAsegurado = requiereAsegurado;
	}
	
}