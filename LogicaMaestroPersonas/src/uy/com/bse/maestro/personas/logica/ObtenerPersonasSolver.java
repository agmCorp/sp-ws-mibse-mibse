package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonas;
import uy.com.bse.maestro.personas.consultas.ResultObtenerPersonas;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerPersonasSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPersonas();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerPersonas((ParamObtenerPersonas) param);
	}

	private ResultObtenerPersonas obtenerPersonas(ParamObtenerPersonas param) {
		return (ResultObtenerPersonas) checkNull(new ServiciosRectorPersist().obtenerPersonas(param));
	}
}
