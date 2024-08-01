package uy.com.bse.maestro.personas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPersonas extends ResultGenerico{
	private ArrayList <DatosPersonaFisica> datosPersonasFisicas;
	private ArrayList <DatosPersonaJuridica> datosPersonasJuridica;
	
	public ResultObtenerPersonas(){
		this.datosPersonasFisicas = new ArrayList <DatosPersonaFisica>();
		this.datosPersonasJuridica = new ArrayList <DatosPersonaJuridica>();
	}
	
	public ArrayList<DatosPersonaFisica> getDatosPersonasFisicas() {
		return datosPersonasFisicas;
	}
	
	public void setDatosPersonasFisicas(
			ArrayList<DatosPersonaFisica> datosPersonasFisicas) {
		this.datosPersonasFisicas = datosPersonasFisicas;
	}	
	
	public ArrayList<DatosPersonaJuridica> getDatosPersonasJuridica() {
		return datosPersonasJuridica;
	}
	
	public void setDatosPersonasJuridica(
			ArrayList<DatosPersonaJuridica> datosPersonasJuridica) {
		this.datosPersonasJuridica = datosPersonasJuridica;
	}
	
	public void setUnaPersonaFisica(DatosPersonaFisica per){
		this.datosPersonasFisicas.add(per);
	}
	
	public void setUnaPersonaJuridica(DatosPersonaJuridica per){
		this.datosPersonasJuridica.add(per);
	}
}
