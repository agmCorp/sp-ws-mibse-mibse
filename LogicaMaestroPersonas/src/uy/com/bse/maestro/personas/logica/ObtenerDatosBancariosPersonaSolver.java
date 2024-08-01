package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.datosbancarios.ParamObtenerDatosBancariosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ResultObtenerDatosBancariosPersona;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDatosBancariosPersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDatosBancariosPersona();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerDatosBancariosPersona((ParamObtenerDatosBancariosPersona) param);
	}

	private ResultObtenerDatosBancariosPersona obtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param) {
		return (ResultObtenerDatosBancariosPersona) checkNull(new ServiciosRectorPersist().obtenerDatosBancariosPersona(param));
	}
}
