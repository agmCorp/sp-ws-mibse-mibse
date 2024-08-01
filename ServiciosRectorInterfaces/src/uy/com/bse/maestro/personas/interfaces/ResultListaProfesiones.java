package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaProfesiones extends ResultGenerico{

	private ArrayList<Profesion> profesion;
	
	public ResultListaProfesiones(){
		this.profesion = new ArrayList<Profesion>(); 
		
	}
	
	public ArrayList<Profesion> getProfesion() {
		return profesion;
	}
	public void setProfesion(ArrayList<Profesion> profesion) {
		this.profesion = profesion;
	}
	
	public void setUnaProfesion(Profesion prof){
		this.profesion.add(prof);
	}
}
