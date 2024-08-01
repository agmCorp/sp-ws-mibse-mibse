package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.personas.ResultPersonaFisica;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDatosPersonaFisicaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPersonaFisica();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerDatosPersonaFisica((ParamPersona) param);
	}

	private ResultPersonaFisica obtenerDatosPersonaFisica(ParamPersona param) {
		return (ResultPersonaFisica) checkNull(new ServiciosRectorPersist().obtenerDatosPersonaFisica(param));
	}
}
