package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ResultListaTipoDoc;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoDocSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTipoDoc ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoDoc((ParamListaTipoDoc) param);
	}

	private ResultListaTipoDoc  listaTipoDoc(ParamListaTipoDoc param) {
		return (ResultListaTipoDoc ) checkNull(new ServiciosRectorPersist().listaTipoDoc(param));
	}
}
