package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ResultListaDepartamentos;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaDepartamentosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaDepartamentos();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaDepartamentos((ParamListaDepartamentos) param);
	}

	private ResultListaDepartamentos listaDepartamentos(ParamListaDepartamentos param) {
		return (ResultListaDepartamentos) checkNull(new ServiciosRectorPersist().listaDepartamentos(param));
	}
}
