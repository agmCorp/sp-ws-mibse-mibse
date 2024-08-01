package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatos extends ResultGenerico{
	private ArrayList<Dato> dato;
	
	public ResultDatos(){
		this.dato = new ArrayList<Dato>();
	}

	public ArrayList<Dato> getDato() {
		return dato;
	}

	public void setDato(ArrayList<Dato> dato) {
		this.dato = dato;
	}
	
	public void setUnItem(Dato item){
		this.dato.add(item);
	}
}
