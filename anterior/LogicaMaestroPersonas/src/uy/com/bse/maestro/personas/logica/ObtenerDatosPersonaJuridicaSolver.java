package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.personas.ResultPersonaJuridica;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDatosPersonaJuridicaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPersonaJuridica();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerDatosPersonaJuridica((ParamPersona) param);
	}

	private ResultPersonaJuridica obtenerDatosPersonaJuridica(ParamPersona param) {
		return (ResultPersonaJuridica) checkNull(new ServiciosRectorPersist().obtenerDatosPersonaJuridica(param));
	}
}
