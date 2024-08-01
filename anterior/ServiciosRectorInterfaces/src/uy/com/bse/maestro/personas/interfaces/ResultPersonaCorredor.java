package uy.com.bse.maestro.personas.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPersonaCorredor extends ResultGenerico{
	
	String codPersona;

	public String getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(String codPersona) {
		this.codPersona = codPersona;
	}
	
	
}
