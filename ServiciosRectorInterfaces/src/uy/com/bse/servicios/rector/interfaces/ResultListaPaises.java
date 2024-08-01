package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaPaises extends ResultGenerico{
	ArrayList<Pais> pais = new ArrayList<Pais>();
	
	public ArrayList<Pais> getPais() {
		return pais;
	}
	public void setPais(ArrayList<Pais> pais) {
		this.pais = pais;
	}
	
	public void setUnPais(Pais p){
		this.pais.add(p);
	}
}
