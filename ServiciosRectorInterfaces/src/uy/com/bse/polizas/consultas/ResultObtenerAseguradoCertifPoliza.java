package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.ContratanteAsegurado;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAseguradoCertifPoliza extends ResultGenerico{
	private ContratanteAsegurado datosAsegurado;

	public ContratanteAsegurado getDatosAsegurado() {
		return datosAsegurado;
	}

	public void setDatosAsegurado(ContratanteAsegurado datosAsegurado) {
		this.datosAsegurado = datosAsegurado;
	}
	
}
