package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignados;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaProductoresAsignadosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCodiguera();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaProductoresAsignados((ParamListaProductoresAsignados) param);
	}

	private ResultCodiguera  listaProductoresAsignados(ParamListaProductoresAsignados param) {
		return (ResultCodiguera ) checkNull(new ServiciosRectorPersist().listaProductoresAsignados(param));
	}
}
