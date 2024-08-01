package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularCertificado extends ResultGenerico{
	
	 private ArrayList<Certificado>listaCertificados = new ArrayList<Certificado>();
	 private ArrayList<String> titulos = new ArrayList<String>();	    
	 private Double montoCuotaCert;
	 private Double montoTotalCert;
	public ArrayList<Certificado> getListaCertificados() {
		return listaCertificados;
	}
	public void setListaCertificados(ArrayList<Certificado> listaCertificados) {
		this.listaCertificados = listaCertificados;
	}
	public ArrayList<String> getTitulos() {
		return titulos;
	}
	public void setTitulos(ArrayList<String> titulos) {
		this.titulos = titulos;
	}
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
	 
	 public void setUnCertificado(Certificado certificado){
		 this.listaCertificados.add(certificado);
	 }

	 public void setUnTitulo(String titulo){
		 this.titulos.add(titulo);
	 }
}
