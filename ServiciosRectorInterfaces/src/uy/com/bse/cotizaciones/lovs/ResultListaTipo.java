package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTipo extends ResultGenerico{
	private ArrayList<Tipo> tipo;
	
	public ResultListaTipo() {		
		this.tipo = new ArrayList<Tipo>();
	}
	
	public ArrayList<Tipo> getTipo() {
		return tipo;
	}

	public void setTipo(ArrayList<Tipo> tipo) {
		this.tipo = tipo;
	}

	public void setUnTipo(Tipo item){
		this.tipo.add(item);
	}
}
