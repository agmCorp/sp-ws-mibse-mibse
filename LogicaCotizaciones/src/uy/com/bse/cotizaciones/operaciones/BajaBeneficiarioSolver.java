package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BajaBeneficiarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultBajaBeneficiario ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return bajaBeneficiario((ParamBajaBeneficiario) param);
	}

	private ResultBajaBeneficiario  bajaBeneficiario(ParamBajaBeneficiario param) {
		return (ResultBajaBeneficiario ) checkNull(new CotOperaciones().bajaBeneficiario(param));
	}
}
