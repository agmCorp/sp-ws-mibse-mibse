package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;

public final class ResultAltaUsuario extends ResultGenerico {

	private static final long serialVersionUID = 1L;
	private String user;

	public ResultAltaUsuario() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
