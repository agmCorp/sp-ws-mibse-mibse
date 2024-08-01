package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamLocalidades;
import uy.com.bse.servicios.rector.interfaces.ResultListaLocalidades;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaLocalidadesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaLocalidades();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaLocalidades((ParamLocalidades) param);
	}

	private ResultListaLocalidades listaLocalidades(ParamLocalidades param) {
		return (ResultListaLocalidades) checkNull(new ServiciosRectorPersist().listaLocalidades(param));
	}
}
