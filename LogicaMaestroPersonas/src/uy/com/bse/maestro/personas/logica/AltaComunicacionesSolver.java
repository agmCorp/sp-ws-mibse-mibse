package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.comunicaciones.ParamAltaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaComunicacionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultComunicacion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaComunicaciones((ParamAltaComunicacion) param);
	}

	private ResultComunicacion altaComunicaciones(ParamAltaComunicacion param) {
		return (ResultComunicacion) checkNull(new ServiciosRectorPersist().altaComunicaciones((param)));
	}
}
