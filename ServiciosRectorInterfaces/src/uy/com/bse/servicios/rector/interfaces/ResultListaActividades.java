package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaActividades extends ResultGenerico{

	ArrayList<Actividad> actividad = new ArrayList<Actividad>();
	

	public ArrayList<Actividad> getActividad() {
		return actividad;
	}
	public void setActividad(ArrayList<Actividad> actividad) {
		this.actividad = actividad;
	}
	
	public void setUnaActivadad(Actividad act){
		this.actividad.add(act);
	}
}
