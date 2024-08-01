package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosXDatoParametrico;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosXDatosParametricosPoliza extends ResultGenerico{
	
	private ArrayList<DatosXDatoParametrico> datos = new ArrayList<DatosXDatoParametrico>();

	public ArrayList<DatosXDatoParametrico> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<DatosXDatoParametrico> datos) {
		this.datos = datos;
	}
	

}
