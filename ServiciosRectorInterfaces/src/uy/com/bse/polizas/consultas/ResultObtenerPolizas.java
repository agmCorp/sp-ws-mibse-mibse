package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizas extends ResultGenerico{

	private static final long serialVersionUID = 7818461896012647380L;
	private ArrayList<DatosPoliza> polizas = new ArrayList<DatosPoliza>();

	public ArrayList<DatosPoliza> getPolizas() {
		return polizas;
	}

	public void setPolizas(ArrayList<DatosPoliza> polizas) {
		this.polizas = polizas;
	}
	
	public void setUnaPoliza(DatosPoliza item){
		this.polizas.add(item);
	}
}
