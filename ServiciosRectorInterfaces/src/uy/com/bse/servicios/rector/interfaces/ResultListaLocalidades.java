package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaLocalidades extends ResultGenerico{

	ArrayList<Localidad> localidad = new ArrayList<Localidad>();
	
	public ArrayList<Localidad> getLocalidad() {
		return localidad;
	}
	public void setLocalidad(ArrayList<Localidad> localidad) {
		this.localidad = localidad;
	}
	public void setUnaLocalidad(Localidad loc){
		this.localidad.add(loc);
	}	
}
