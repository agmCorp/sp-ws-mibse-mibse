package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.DatosBasicosPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosPoliza extends ResultGenerico{
	private DatosBasicosPoliza datosPoliza;

	public DatosBasicosPoliza getDatosPoliza() {
		return datosPoliza;
	}

	public void setDatosPoliza(DatosBasicosPoliza datosPoliza) {
		this.datosPoliza = datosPoliza;
	}

}
