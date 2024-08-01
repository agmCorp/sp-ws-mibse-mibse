package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTipoComunicaciones extends ResultGenerico{
	private ArrayList<Comunicacion> comunicacion;
	
	public ResultListaTipoComunicaciones(){
		this.comunicacion = new ArrayList<Comunicacion>();
		
	}
	
	public ArrayList<Comunicacion> getComunicacion() {
		return comunicacion;
	}
	public void setComunicacion(ArrayList<Comunicacion> comunicacion) {
		this.comunicacion = comunicacion;
	}
	
	public void setUnaComunicacion(Comunicacion com){
		this.comunicacion.add(com);
	}
}
