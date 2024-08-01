package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultFueraPautas extends ResultGenerico{
	private ArrayList<FueraPauta> fueraPauta;

	public ResultFueraPautas(){
		this.fueraPauta = new ArrayList<FueraPauta>();
	}
	
	public ArrayList<FueraPauta> getFueraPauta() {
		return fueraPauta;
	}

	public void setFueraPauta(ArrayList<FueraPauta> fueraPauta) {
		this.fueraPauta = fueraPauta;
	}
	
	public void setUnElemento(FueraPauta item){
		this.fueraPauta.add(item);
	}
}
