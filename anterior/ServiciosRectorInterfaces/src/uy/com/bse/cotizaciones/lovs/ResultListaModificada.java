package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaModificada extends ResultGenerico{
	private ArrayList<Modificada> modificada;
	
	public ResultListaModificada() {		
		this.modificada = new ArrayList<Modificada>();
	}	

	public ArrayList<Modificada> getModificada() {
		return modificada;
	}

	public void setModificada(ArrayList<Modificada> modificada) {
		this.modificada = modificada;
	}

	public void setUnModificada(Modificada item){
		this.modificada.add(item);
	}
}
