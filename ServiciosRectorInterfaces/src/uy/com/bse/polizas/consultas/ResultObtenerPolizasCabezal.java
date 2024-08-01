package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizasCabezal extends ResultGenerico{
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
