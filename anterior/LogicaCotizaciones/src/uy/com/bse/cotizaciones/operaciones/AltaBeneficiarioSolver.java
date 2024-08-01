package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaBeneficiarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaBeneficiario ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return altaBeneficiario((ParamAltaBeneficiario) param);
	}

	private ResultAltaBeneficiario  altaBeneficiario(ParamAltaBeneficiario param) {
		return (ResultAltaBeneficiario ) checkNull(new CotOperaciones().altaBeneficiario(param));
	}
}
