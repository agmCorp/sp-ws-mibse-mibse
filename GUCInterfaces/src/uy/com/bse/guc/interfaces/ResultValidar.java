package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;


public final class ResultValidar extends ResultGenerico {

	private static final long serialVersionUID = 1L;
	private String user;

	public ResultValidar() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
