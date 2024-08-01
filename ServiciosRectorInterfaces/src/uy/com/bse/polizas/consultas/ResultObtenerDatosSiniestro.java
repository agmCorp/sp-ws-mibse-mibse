package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosSiniestro extends ResultGenerico{
	private DatosSiniestro datosSiniestro;

	public DatosSiniestro getDatosSiniestro() {
		return datosSiniestro;
	}

	public void setDatosSiniestro(DatosSiniestro datosSiniestro) {
		this.datosSiniestro = datosSiniestro;
	}

}
