package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaFisica;
import uy.com.bse.maestro.personas.personas.ResultPersonaFisica;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarPersonaFisicaSolver extends AbstractSolver {
//FIXME OIGRES PARA EL PARSEOOO del solver
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPersonaFisica();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return modificarPersonaFisica((ParamDatosPersonaFisica) param);
	}

	private ResultPersonaFisica modificarPersonaFisica(ParamDatosPersonaFisica param) {
		return (ResultPersonaFisica) checkNull(new ServiciosRectorPersist().modificarPersonaFisica((param)));
	}
}
