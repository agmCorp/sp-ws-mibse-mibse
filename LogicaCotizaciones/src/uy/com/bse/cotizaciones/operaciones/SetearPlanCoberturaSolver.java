package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class SetearPlanCoberturaSolver extends AbstractSolver {
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(setearPlanCobertura((ParamSetearPlanCobertura) param));
	}

	private ResultGenerico setearPlanCobertura(ParamSetearPlanCobertura myParam) {
		return new CotOperaciones().setearPlanCobertura(myParam);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

}
