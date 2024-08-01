package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTipoDireccion extends ResultGenerico{

	private ArrayList<Direccion> direccion;
	
	public ResultListaTipoDireccion(){
		this.direccion = new ArrayList<Direccion>();
		
	}
	
	public ArrayList<Direccion> getDireccion() {
		return direccion;
	}
	public void setDireccion(ArrayList<Direccion> direccion) {
		this.direccion = direccion;
	}
	
	public void setUnaDireccion(Direccion dir){
		this.direccion.add(dir);
	}
}
