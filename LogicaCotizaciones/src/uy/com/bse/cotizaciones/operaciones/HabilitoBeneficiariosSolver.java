package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoBeneficiariosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoBeneficiarios ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoBeneficiarios((ParamHabilitoBeneficiarios) param);
	}

	private ResultHabilitoBeneficiarios  habilitoBeneficiarios(ParamHabilitoBeneficiarios param) {
		return (ResultHabilitoBeneficiarios ) checkNull(new CotOperaciones().habilitoBeneficiarios(param));
	}
}
