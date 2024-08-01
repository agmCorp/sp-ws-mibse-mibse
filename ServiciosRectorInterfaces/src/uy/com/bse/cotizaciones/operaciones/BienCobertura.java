package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.Cobertura;

public class BienCobertura extends BienCert{

	
	private ArrayList<Cobertura> coberturaBien = new ArrayList<Cobertura>();
	private String etiquetaCapital;

	
	public BienCobertura(){
		
		super();
	}
	
	
	public ArrayList<Cobertura> getCoberturaBien() {
		return coberturaBien;
	}



	public void setCoberturaBien(ArrayList<Cobertura> coberturaBien) {
		this.coberturaBien = coberturaBien;
	}



	public void setUnElemento(Cobertura item){
		this.coberturaBien.add(item);
	}


	public String getEtiquetaCapital() {
		return etiquetaCapital;
	}


	public void setEtiquetaCapital(String etiquetaCapital) {
		this.etiquetaCapital = etiquetaCapital;
	}
	
}
