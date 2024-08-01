package uy.com.bse.maestro.personas.comunicaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultComunicacion extends ResultGenerico{
	
	private ComunicacionEC comunicacionPersona;

	public ComunicacionEC getComunicacionPersona() {
		return comunicacionPersona;
	}

	public void setComunicacionPersona(ComunicacionEC comunicacionPersona) {
		this.comunicacionPersona = comunicacionPersona;
	}
	
	
}
