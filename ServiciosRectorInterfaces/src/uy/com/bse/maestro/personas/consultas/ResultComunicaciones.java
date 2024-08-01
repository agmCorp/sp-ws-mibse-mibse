package uy.com.bse.maestro.personas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultComunicaciones extends ResultGenerico{
	private ArrayList<DatosComunicacion> comunicacionesPersona;
		
	
	public ResultComunicaciones(){
		this.comunicacionesPersona = new ArrayList<DatosComunicacion>();
	}

	public ArrayList<DatosComunicacion> getComunicacionesPersona() {
		return comunicacionesPersona;
	}

	public void setComunicacionesPersona(
			ArrayList<DatosComunicacion> comunicacionesPersona) {
		this.comunicacionesPersona = comunicacionesPersona;
	}
	
	public void setUnaComunicacion(DatosComunicacion com){
		this.comunicacionesPersona.add(com);
	}
}
