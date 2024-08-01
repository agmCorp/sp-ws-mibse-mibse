package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.DatosPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizaCabezalDetalle extends ResultGenerico{

	private static final long serialVersionUID = -8515347601081728716L;
	private DatosPoliza poliza;

	public DatosPoliza getPoliza() {
		return poliza;
	}

	public void setPoliza(DatosPoliza poliza) {
		this.poliza = poliza;
	}

	
}
