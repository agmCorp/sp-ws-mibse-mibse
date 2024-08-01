package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.rol.ParamDatosRolCliente;
import uy.com.bse.maestro.personas.rol.ResultRolCliente;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarRolClienteSolver extends AbstractSolver {
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultRolCliente();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return modificarDatosRolCliente((ParamDatosRolCliente) param);
	}

	private ResultRolCliente modificarDatosRolCliente(ParamDatosRolCliente param) {
		return (ResultRolCliente) checkNull(new ServiciosRectorPersist().modificarDatosRolCliente((param)));
	}
}
