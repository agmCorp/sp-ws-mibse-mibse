package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public final class ParamValidar extends ParamGenerico {

	private static final long serialVersionUID = 6609208740613959505L;
	private String claveUsuario;

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

}
