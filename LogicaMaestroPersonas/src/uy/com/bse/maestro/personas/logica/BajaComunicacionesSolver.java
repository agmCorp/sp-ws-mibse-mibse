package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.comunicaciones.ParamBajaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacionEC;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BajaComunicacionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultComunicacionEC();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return bajaComunicaciones((ParamBajaComunicacion) param);
	}

	private ResultComunicacionEC bajaComunicaciones(ParamBajaComunicacion param) {
		return (ResultComunicacionEC) checkNull(new ServiciosRectorPersist().bajaComunicaciones((param)));
	}
}
