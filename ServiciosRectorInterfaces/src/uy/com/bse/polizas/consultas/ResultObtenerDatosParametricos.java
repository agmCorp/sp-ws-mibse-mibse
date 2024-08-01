package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatoParametricoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosParametricos extends ResultGenerico{
	
	private static final long serialVersionUID = -7965562197320297921L;
	private ArrayList<DatoParametricoPoliza> datos = new ArrayList<DatoParametricoPoliza>();
	
	public ArrayList<DatoParametricoPoliza> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<DatoParametricoPoliza> datos) {
		this.datos = datos;
	}
	
	public void setUno(DatoParametricoPoliza dato){
		datos.add(dato);
	}
	
	
}
