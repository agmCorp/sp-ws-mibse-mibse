package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.Siniestro;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDeclaracionSiniestro extends ResultGenerico{
	private Siniestro siniestro;

	public Siniestro getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(Siniestro siniestro) {
		this.siniestro = siniestro;
	}
	
}
