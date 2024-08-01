package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamPersonaCorredor;
import uy.com.bse.maestro.personas.interfaces.ResultPersonaCorredor;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerPersonaCorredorSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPersonaCorredor();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerPersonaCorredor((ParamPersonaCorredor) param);
	}

	private ResultPersonaCorredor obtenerPersonaCorredor(ParamPersonaCorredor param) {
		return (ResultPersonaCorredor) checkNull(new ServiciosRectorPersist().obtenerPersonaCorredor(param));
	}
}
