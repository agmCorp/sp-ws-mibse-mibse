package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.consultas.ResultComunicaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerComunicacionesDeUnaPersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultComunicaciones();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerComunicacionesDeUnaPersona((ParamPersona) param);
	}

	private ResultComunicaciones obtenerComunicacionesDeUnaPersona(ParamPersona param) {
		return (ResultComunicaciones) checkNull(new ServiciosRectorPersist().obtenerComunicacionesDeUnaPersona(param));
	}
}
