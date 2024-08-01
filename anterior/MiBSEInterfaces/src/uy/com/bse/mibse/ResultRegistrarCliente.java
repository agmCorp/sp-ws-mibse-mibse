package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRegistrarCliente extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
