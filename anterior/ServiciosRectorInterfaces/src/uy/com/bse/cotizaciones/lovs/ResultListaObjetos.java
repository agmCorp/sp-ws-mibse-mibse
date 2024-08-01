package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaObjetos extends ResultGenerico{
	private ArrayList<Objeto> objeto;
	
	public ResultListaObjetos(){
		this.objeto = new ArrayList<Objeto>();
	}

	public ArrayList<Objeto> getObjeto() {
		return objeto;
	}

	public void setObjeto(ArrayList<Objeto> objeto) {
		this.objeto = objeto;
	}
	
	
	public void setUnElemento(Objeto item){
		this.objeto.add(item);
	}
	
}
