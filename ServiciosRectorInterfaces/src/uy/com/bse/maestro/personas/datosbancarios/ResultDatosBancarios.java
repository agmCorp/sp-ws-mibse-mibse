package uy.com.bse.maestro.personas.datosbancarios;

import java.util.ArrayList;

import uy.com.bse.maestro.personas.consultas.DatosComunicacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosBancarios extends ResultGenerico {
private ArrayList<DatosComunicacion> comunicacionesPersona;
		
	
	public ResultDatosBancarios(){
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
