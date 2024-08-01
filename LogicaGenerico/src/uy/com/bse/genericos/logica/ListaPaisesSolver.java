package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaPaises;
import uy.com.bse.servicios.rector.interfaces.ResultListaPaises;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaPaisesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaPaises ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaPaises((ParamListaPaises) param);
	}

	private ResultListaPaises  listaPaises(ParamListaPaises param) {
		return (ResultListaPaises ) checkNull(new ServiciosRectorPersist().listaPaises(param));
	}
}
