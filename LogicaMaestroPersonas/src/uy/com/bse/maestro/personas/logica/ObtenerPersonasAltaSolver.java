package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamObtenerPersonasAlta;
import uy.com.bse.maestro.personas.interfaces.ResultObtenerPersonasAlta;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerPersonasAltaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPersonasAlta();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerPersonasAlta((ParamObtenerPersonasAlta) param);
	}

	private ResultObtenerPersonasAlta obtenerPersonasAlta(ParamObtenerPersonasAlta param) {
		return (ResultObtenerPersonasAlta) checkNull(new ServiciosRectorPersist().obtenerPersonasAlta(param));
	}
}
