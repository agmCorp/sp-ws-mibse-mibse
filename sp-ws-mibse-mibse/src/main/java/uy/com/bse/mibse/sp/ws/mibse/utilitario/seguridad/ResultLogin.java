package uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

public class ResultLogin extends ResultGenerico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
