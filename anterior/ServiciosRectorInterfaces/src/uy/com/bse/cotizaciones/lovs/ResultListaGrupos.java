package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaGrupos extends ResultGenerico {
	private ArrayList<Grupo> grupos = new ArrayList<Grupo>();

	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public void setUnElemento(Grupo item){
		this.grupos.add(item);
	}
	
}
