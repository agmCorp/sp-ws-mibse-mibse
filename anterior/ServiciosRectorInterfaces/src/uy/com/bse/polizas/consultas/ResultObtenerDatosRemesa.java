package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.Remesa;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosRemesa extends ResultGenerico{
	private Remesa datosRemesa;

	public Remesa getDatosRemesa() {
		return datosRemesa;
	}

	public void setDatosRemesa(Remesa datosRemesa) {
		this.datosRemesa = datosRemesa;
	}
}
