package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.DatosParticularesEndoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosEndosoSinPremio extends ResultGenerico{
	
	private DatosParticularesEndoso datosParticulares = new DatosParticularesEndoso();

	public DatosParticularesEndoso getDatosParticulares() {
		return datosParticulares;
	}

	public void setDatosParticulares(DatosParticularesEndoso datosParticulares) {
		this.datosParticulares = datosParticulares;
	}	
}
