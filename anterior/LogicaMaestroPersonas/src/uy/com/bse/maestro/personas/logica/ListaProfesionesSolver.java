package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamProfesiones;
import uy.com.bse.maestro.personas.interfaces.ResultListaProfesiones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaProfesionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaProfesiones();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaProfesiones((ParamProfesiones) param);
	}

	private ResultListaProfesiones listaProfesiones(ParamProfesiones param) {
		return (ResultListaProfesiones) checkNull(new ServiciosRectorPersist().listaProfesiones(param));
	}
}
