package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamListaSexo;
import uy.com.bse.maestro.personas.interfaces.ResultListaSexo;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaSexoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaSexo();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaSexo((ParamListaSexo) param);
	}

	private ResultListaSexo listaSexo(ParamListaSexo param) {
		return (ResultListaSexo) checkNull(new ServiciosRectorPersist().listaSexo(param));
	}
}
