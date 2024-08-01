package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRecuotificarPoliza extends ResultGenerico{
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
