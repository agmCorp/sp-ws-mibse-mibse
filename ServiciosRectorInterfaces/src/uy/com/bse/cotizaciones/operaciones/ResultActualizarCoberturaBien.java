package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Cobertura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultActualizarCoberturaBien extends ResultGenerico{
	
	private String xml;
	private Double valorActualizado;
	private String derivadosMismoBien;
	private String derivadosOtroBien;
	private ArrayList<Cobertura> coberturas = new ArrayList<Cobertura>();
	
	
	public Double getValorActualizado() {
		return valorActualizado;
	}
	public void setValorActualizado(Double valorActualizado) {
		this.valorActualizado = valorActualizado;
	}
	public String getDerivadosMismoBien() {
		return derivadosMismoBien;
	}
	public void setDerivadosMismoBien(String derivadosMismoBien) {
		this.derivadosMismoBien = derivadosMismoBien;
	}
	public String getDerivadosOtroBien() {
		return derivadosOtroBien;
	}
	public void setDerivadosOtroBien(String derivadosOtroBien) {
		this.derivadosOtroBien = derivadosOtroBien;
	}
	public ArrayList<Cobertura> getCoberturas() {
		return coberturas;
	}
	public void setCoberturas(ArrayList<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}
	
	public void setUnaCobertura(Cobertura cobertura){
		this.coberturas.add(cobertura);
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	

}
