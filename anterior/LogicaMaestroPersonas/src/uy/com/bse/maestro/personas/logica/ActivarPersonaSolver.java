package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.personas.ParamDatosActivarPersona;
import uy.com.bse.maestro.personas.personas.ResultActivarPersona;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActivarPersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActivarPersona();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return activarPersona((ParamDatosActivarPersona) param);
	}

	private ResultActivarPersona activarPersona(ParamDatosActivarPersona param) {
		return (ResultActivarPersona) checkNull(new ServiciosRectorPersist().activarPersona(param));
	}
}
