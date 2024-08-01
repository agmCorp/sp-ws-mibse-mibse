package uy.com.bse.autodata.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosAuto extends ResultGenerico {

	private static final long serialVersionUID = 7430614337362800150L;
	
	private  ArrayList<DatosAuto> datosAutos = new ArrayList<DatosAuto>();

	public ArrayList<DatosAuto> getDatosAutos() {
		return datosAutos;
	}

	public void setDatosAutos(ArrayList<DatosAuto> datosAutos) {
		this.datosAutos = datosAutos;
	}
	
	public void setUno(DatosAuto dato){
		this.datosAutos.add(dato);
	}

}
