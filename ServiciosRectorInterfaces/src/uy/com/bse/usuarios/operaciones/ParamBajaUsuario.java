package uy.com.bse.usuarios.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamBajaUsuario extends ParamGenerico {
	private String cedula;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}