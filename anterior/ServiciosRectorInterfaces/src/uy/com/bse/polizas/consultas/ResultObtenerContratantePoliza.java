package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.ContratanteAsegurado;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerContratantePoliza extends ResultGenerico{
	private ContratanteAsegurado datosContratante ;

	public ContratanteAsegurado getDatosContratante() {
		return datosContratante;
	}

	public void setDatosContratante(ContratanteAsegurado datosContratante) {
		this.datosContratante = datosContratante;
	}
	
}
