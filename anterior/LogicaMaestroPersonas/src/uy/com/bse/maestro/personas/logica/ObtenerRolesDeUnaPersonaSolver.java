package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.consultas.ResultRoles;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerRolesDeUnaPersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultRoles();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerRolesDeUnaPersona((ParamPersona) param);
	}

	private ResultRoles obtenerRolesDeUnaPersona(ParamPersona param) {
		return (ResultRoles) checkNull(new ServiciosRectorPersist().obtenerRolesDeUnaPersona(param));
	}
}
