package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.domicilio.ResultDireccion;
import uy.com.bse.maestro.personas.personas.ParamObtenerDireccion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDatosDeUnaDireccionSolver extends AbstractSolver {
	
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDireccion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerDatosDeUnaDireccion((ParamObtenerDireccion) param);
	}

	private ResultDireccion obtenerDatosDeUnaDireccion(ParamObtenerDireccion param) {
		return (ResultDireccion) checkNull(new ServiciosRectorPersist().obtenerDatosDeUnaDireccion(param));
	}
}
