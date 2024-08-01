package uy.com.bse.autodata.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerModelosAutodata extends ResultGenerico{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9165277867784118392L;

	
	private  ArrayList<Modelo> datosModeloAutos = new ArrayList<Modelo>();

	public ArrayList<Modelo> getDatosAutos() {
		return datosModeloAutos;
	}

	public void setDatosAutos(ArrayList<Modelo> datosAutos) {
		this.datosModeloAutos = datosAutos;
	}
	
	public void setUno(Modelo dato){
		this.datosModeloAutos.add(dato);
	}
}
