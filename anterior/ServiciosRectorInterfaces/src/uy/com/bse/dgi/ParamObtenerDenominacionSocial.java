package uy.com.bse.dgi;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDenominacionSocial extends ParamGenerico {

	private static final long serialVersionUID = -4133006203176189907L;
	
	private String numRut;

	public String getNumRut() {
		return numRut;
	}

	public void setNumRut(String numRut) {
		this.numRut = numRut;
	}

}
