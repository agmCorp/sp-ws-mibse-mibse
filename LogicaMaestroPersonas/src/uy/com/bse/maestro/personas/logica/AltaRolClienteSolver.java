package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.personas.ParamAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ResultAltaRolCliente;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaRolClienteSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaRolCliente();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaRolCliente((ParamAltaRolCliente) param);
	}

	private ResultAltaRolCliente altaRolCliente(ParamAltaRolCliente param) {
		return (ResultAltaRolCliente) checkNull(new ServiciosRectorPersist().altaRolCliente(param));
	}
}
