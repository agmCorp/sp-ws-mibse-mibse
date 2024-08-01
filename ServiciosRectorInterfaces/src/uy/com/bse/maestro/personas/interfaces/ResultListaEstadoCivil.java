package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaEstadoCivil extends ResultGenerico{

	ArrayList<EstadoCivil> estadoCivil;
	
	public ResultListaEstadoCivil(){
		this.estadoCivil = new ArrayList<EstadoCivil>();	
	}
	
	public ArrayList<EstadoCivil> getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(ArrayList<EstadoCivil> estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public void setUnEstadoCivil(EstadoCivil est){
		this.estadoCivil.add(est);
	}
}
