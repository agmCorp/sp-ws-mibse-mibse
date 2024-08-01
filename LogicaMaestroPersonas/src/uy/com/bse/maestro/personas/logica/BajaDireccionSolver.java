package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.domicilio.ParamDatosBajaDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultBajaDireccion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BajaDireccionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultBajaDireccion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return bajaDireccion((ParamDatosBajaDireccion) param);
	}

	private ResultBajaDireccion bajaDireccion(ParamDatosBajaDireccion param) {
		return (ResultBajaDireccion) checkNull(new ServiciosRectorPersist().bajaDireccion((param)));
	}
}
