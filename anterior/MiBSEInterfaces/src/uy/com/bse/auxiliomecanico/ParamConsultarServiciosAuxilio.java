package uy.com.bse.auxiliomecanico;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamConsultarServiciosAuxilio extends ParamGenerico {

	private static final long serialVersionUID = 6765921097981181895L;
	
	private int nroPersona;

	public int getNroPersona() {
		return nroPersona;
	}

	public void setNroPersona(int nroPersona) {
		this.nroPersona = nroPersona;
	}
	
}
