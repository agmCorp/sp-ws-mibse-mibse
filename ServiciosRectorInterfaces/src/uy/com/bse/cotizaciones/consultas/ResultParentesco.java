package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;


public class ResultParentesco extends ResultGenerico {
	private ArrayList<Parentesco> parentescos = new ArrayList<Parentesco>();
	
	public ArrayList<Parentesco> getParentescos() {
		return parentescos;
	}

	public void setParentescos(ArrayList<Parentesco> parentescos) {
		this.parentescos = parentescos;
	}


	public void setUnElemento(Parentesco item){
		this.parentescos.add(item);
	}

}
