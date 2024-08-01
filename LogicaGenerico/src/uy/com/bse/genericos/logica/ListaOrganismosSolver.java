package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ResultListaOrganismos;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaOrganismosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaOrganismos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaOrganismos((ParamListaOrganismos) param);
	}

	private ResultListaOrganismos  listaOrganismos(ParamListaOrganismos param) {
		return (ResultListaOrganismos ) checkNull(new ServiciosRectorPersist().listaOrganismos(param));
	}
}
