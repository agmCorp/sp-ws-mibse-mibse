package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPersonaJuridica extends ResultGenerico{
	private PersonaJuridica persona;

	public ResultPersonaJuridica(){
		this.persona = new PersonaJuridica();
	}
	
	public PersonaJuridica getPersona(){
		return persona;
	}

	public void setPersona(PersonaJuridica persona){
		this.persona = persona;
	}		
}
