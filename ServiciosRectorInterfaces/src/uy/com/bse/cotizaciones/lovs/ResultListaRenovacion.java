package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaRenovacion extends ResultGenerico{
	private ArrayList<Renovacion> renovacion;
	
	public ResultListaRenovacion() {		
		this.renovacion = new ArrayList<Renovacion>();
	}

	public ArrayList<Renovacion> getRenovacion() {
		return renovacion;
	}

	public void setRenovacion(ArrayList<Renovacion> renovacion) {
		this.renovacion = renovacion;
	}

	public void setUnElemento(Renovacion item){
		this.renovacion.add(item);
	}
}
