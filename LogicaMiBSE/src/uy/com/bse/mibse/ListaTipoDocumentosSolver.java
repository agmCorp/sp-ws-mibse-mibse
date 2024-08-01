package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoDocumentosSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoDocumentos((ParamListaTipoDocumentos) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCodiguera();
	}

	private ResultCodiguera listaTipoDocumentos(ParamListaTipoDocumentos param) {
		return (ResultCodiguera) checkNull(new ServiciosMiBsePersist().listaTipoDocumentos(param));
	}
}
