package uy.com.bse.vehiculos;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultInformarDatosVehiculo extends ResultGenerico {
	private static final long serialVersionUID = -7331856511423982469L;
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
