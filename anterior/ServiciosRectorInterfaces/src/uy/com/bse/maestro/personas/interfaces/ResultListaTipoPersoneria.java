package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTipoPersoneria extends ResultGenerico{
	private ArrayList<Personeria> personeria;
	
	public ResultListaTipoPersoneria(){
		this.personeria = new ArrayList<Personeria>();
		
	}
	
	public ArrayList<Personeria> getPersoneria() {
		return personeria;
	}
	public void setPersoneria(ArrayList<Personeria> personeria) {
		this.personeria = personeria;
	}
	
	public void setUnaPersoneria(Personeria per){
		this.personeria.add(per);
	}
}
