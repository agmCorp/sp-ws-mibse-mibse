package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaJuridica;
import uy.com.bse.maestro.personas.personas.ResultPersonaJuridica;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaPersonaJuridicaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPersonaJuridica();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaPersonaJuridica((ParamDatosPersonaJuridica) param);
	}

	private ResultPersonaJuridica altaPersonaJuridica(ParamDatosPersonaJuridica param) {
		return (ResultPersonaJuridica) checkNull(new ServiciosRectorPersist().altaPersonaJuridica((param)));
	}
}
