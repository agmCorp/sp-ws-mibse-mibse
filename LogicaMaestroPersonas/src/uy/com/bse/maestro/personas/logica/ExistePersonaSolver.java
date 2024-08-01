package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.personas.ParamExistePersona;
import uy.com.bse.maestro.personas.personas.ResultExistePersona;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ExistePersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultExistePersona();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return existePersona((ParamExistePersona) param);
	}

	private ResultExistePersona existePersona(ParamExistePersona param) {
		return (ResultExistePersona) checkNull(new ServiciosRectorPersist().existePersona(param));
	}
}
