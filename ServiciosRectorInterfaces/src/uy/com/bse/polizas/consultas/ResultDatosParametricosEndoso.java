package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatoParametricoEndoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosParametricosEndoso extends ResultGenerico{
	
	private ArrayList<DatoParametricoEndoso> datosParametricos = new ArrayList<DatoParametricoEndoso>();

	public ArrayList<DatoParametricoEndoso> getDatosParametricos() {
		return datosParametricos;
	}

	public void setDatosParametricos(
			ArrayList<DatoParametricoEndoso> datosParametricos) {
		this.datosParametricos = datosParametricos;
	}	
	
}
