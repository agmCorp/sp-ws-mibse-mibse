package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.comunicaciones.ParamModificarComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarComunicacionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultComunicacion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return modificarComunicaciones((ParamModificarComunicacion) param);
	}

	private ResultComunicacion modificarComunicaciones(ParamModificarComunicacion param) {
		return (ResultComunicacion) checkNull(new ServiciosRectorPersist().modificarComunicaciones((param)));
	}
}
