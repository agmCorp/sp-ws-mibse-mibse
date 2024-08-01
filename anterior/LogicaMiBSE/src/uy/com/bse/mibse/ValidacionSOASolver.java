package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ValidacionSOASolver extends AbstractSolver {
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		ResultGenerico res = checkNull(new ServiciosMiBsePersist().validacionSOA((ParamValidacionSOA) param));
		return res;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidacionSOA();
	}
}
