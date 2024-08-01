package uy.com.bse.guc.logica;


import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class ValidarSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidar();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(DataProviderUtil.getDataProvider().validar((ParamValidar) param));
	}

		
	
}
