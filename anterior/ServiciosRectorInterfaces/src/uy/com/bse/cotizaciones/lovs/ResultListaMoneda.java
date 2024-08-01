package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaMoneda extends ResultGenerico{
	private ArrayList<Moneda> moneda;
	
	public ResultListaMoneda() {		
		this.moneda = new ArrayList<Moneda>();
	}

	public ArrayList<Moneda> getMoneda() {
		return moneda;
	}

	public void setMoneda(ArrayList<Moneda> renovacion) {
		this.moneda = renovacion;
	}

	public void setUnElemento(Moneda item){
		this.moneda.add(item);
	}
}
