package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.domicilio.ParamDatosDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultDireccion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaDireccionSolver extends AbstractSolver {
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDireccion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaDireccion((ParamDatosDireccion) param);
	}

	private ResultDireccion altaDireccion(ParamDatosDireccion param) {
		return (ResultDireccion) checkNull(new ServiciosRectorPersist().altaDireccion((param)));
	}
}
