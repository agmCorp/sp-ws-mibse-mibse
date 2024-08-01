package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamTipoPersoneria;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoPersoneria;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoPersoneriaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTipoPersoneria();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaTipoPersoneria((ParamTipoPersoneria) param);
	}

	private ResultListaTipoPersoneria listaTipoPersoneria(ParamTipoPersoneria param) {
		return (ResultListaTipoPersoneria) checkNull(new ServiciosRectorPersist().listaTipoPersoneria(param));
	}
}
