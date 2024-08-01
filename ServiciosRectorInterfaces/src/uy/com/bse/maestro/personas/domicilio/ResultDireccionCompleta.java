package uy.com.bse.maestro.personas.domicilio;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDireccionCompleta extends ResultGenerico{
	
	private ArrayList<DireccionEC> direcciones = new ArrayList<DireccionEC>();

	public ArrayList<DireccionEC> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(ArrayList<DireccionEC> direcciones) {
		this.direcciones = direcciones;
	}
	
	

}
