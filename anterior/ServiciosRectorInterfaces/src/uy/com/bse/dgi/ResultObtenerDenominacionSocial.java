package uy.com.bse.dgi;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDenominacionSocial extends ResultGenerico {

	private static final long serialVersionUID = 1338966918550809402L;
	
	private String denominacioSocial;

	public String getDenominacioSocial() {
		return denominacioSocial;
	}

	public void setDenominacioSocial(String denominacioSocial) {
		this.denominacioSocial = denominacioSocial;
	}

}
