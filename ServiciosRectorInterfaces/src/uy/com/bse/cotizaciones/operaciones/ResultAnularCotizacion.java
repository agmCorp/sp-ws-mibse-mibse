package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAnularCotizacion extends ResultGenerico{
	
	ArrayList<Integer> cotizacionesAnuladas = new ArrayList<Integer>();
	ArrayList<Integer> cotizacionesNoAnuladas = new ArrayList<Integer>();
	public ArrayList<Integer> getCotizacionesAnuladas() {
		return cotizacionesAnuladas;
	}
	public void setCotizacionesAnuladas(ArrayList<Integer> cotizacionesAnuladas) {
		this.cotizacionesAnuladas = cotizacionesAnuladas;
	}
	public ArrayList<Integer> getCotizacionesNoAnuladas() {
		return cotizacionesNoAnuladas;
	}
	public void setCotizacionesNoAnuladas(ArrayList<Integer> cotizacionesNoAnuladas) {
		this.cotizacionesNoAnuladas = cotizacionesNoAnuladas;
	}
		

}
