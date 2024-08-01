package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamEstadoCivil;
import uy.com.bse.maestro.personas.interfaces.ResultListaEstadoCivil;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaEstadoCivilSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaEstadoCivil();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaEstadoCivil((ParamEstadoCivil) param);
	}

	private ResultListaEstadoCivil listaEstadoCivil(ParamEstadoCivil param) {
		return (ResultListaEstadoCivil) checkNull(new ServiciosRectorPersist().listaEstadoCivil(param));
	}
}
