package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPersonaFisica extends ResultGenerico{
	private PersonaFisica persona;

	public ResultPersonaFisica(){
		this.persona = new PersonaFisica();
	}
	
	public PersonaFisica getPersona() {
		return persona;
	}

	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}
}
