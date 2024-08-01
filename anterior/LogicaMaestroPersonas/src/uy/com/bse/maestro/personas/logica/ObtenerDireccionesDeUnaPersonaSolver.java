package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.personas.ResultObtenerDirecciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDireccionesDeUnaPersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDirecciones();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerDireccionesDeUnaPersona((ParamPersona) param);
	}

	private ResultObtenerDirecciones obtenerDireccionesDeUnaPersona(ParamPersona param) {
		return (ResultObtenerDirecciones) checkNull(new ServiciosRectorPersist().obtenerDireccionesDeUnaPersona(param));
	}
}
