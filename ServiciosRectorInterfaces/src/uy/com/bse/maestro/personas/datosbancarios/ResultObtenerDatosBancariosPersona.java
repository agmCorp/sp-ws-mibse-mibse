package uy.com.bse.maestro.personas.datosbancarios;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosBancariosPersona extends ResultGenerico{

	private ArrayList<DatosBanco> datosBanco = new ArrayList<DatosBanco>();
	

	
	public ArrayList<DatosBanco> getDatosBanco() {
		return datosBanco;
	}
	public void setDatosBanco(ArrayList<DatosBanco> datosBanco) {
		this.datosBanco = datosBanco;
	}
	public void setUnDatosBanco(DatosBanco datosBanco){
		this.datosBanco.add(datosBanco);
	}

}
