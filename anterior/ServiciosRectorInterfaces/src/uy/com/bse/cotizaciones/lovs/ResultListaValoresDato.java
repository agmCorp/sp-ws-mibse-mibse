package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaValoresDato extends ResultGenerico {
	ArrayList<ValorDato> listaValoresDato = new ArrayList<ValorDato>();

	public ArrayList<ValorDato> getListaValoresDato() {
		return listaValoresDato;
	}

	public void setListaValoresDato(ArrayList<ValorDato> listaValoresDato) {
		this.listaValoresDato = listaValoresDato;
	}
	
	public void setUnElemento(ValorDato item){
		this.listaValoresDato.add(item);
	}
}
