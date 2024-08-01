package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Componente;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerComponentes extends ResultGenerico{
	private ArrayList<Componente> listaComponentes = new ArrayList<Componente>();

	public ArrayList<Componente> getListaComponentes() {
		return listaComponentes;
	}

	public void setListaComponentes(ArrayList<Componente> listaComponentes) {
		this.listaComponentes = listaComponentes;
	}
	
	public void setUnElemento(Componente item){
		this.listaComponentes.add(item);
	}
}
