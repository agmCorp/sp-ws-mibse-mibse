package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaActividades;
import uy.com.bse.servicios.rector.interfaces.ResultListaActividades;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaActividadesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaActividades();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaActividades((ParamListaActividades) param);
	}

	private ResultListaActividades listaActividades(ParamListaActividades param) {
		return (ResultListaActividades) checkNull(new ServiciosRectorPersist().listaActividades(param));
	}
}
