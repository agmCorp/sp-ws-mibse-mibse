package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamTipoDireccion;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoDireccion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoDireccionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTipoDireccion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaTipoDireccion((ParamTipoDireccion) param);
	}

	private ResultListaTipoDireccion listaTipoDireccion(ParamTipoDireccion param) {
		return (ResultListaTipoDireccion) checkNull(new ServiciosRectorPersist().listaTipoDireccion(param));
	}
}
