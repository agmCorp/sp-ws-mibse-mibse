package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarBeneficiarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificarBeneficiario ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return modificarBeneficiario((ParamModificarBeneficiario) param);
	}

	private ResultModificarBeneficiario  modificarBeneficiario(ParamModificarBeneficiario param) {
		return (ResultModificarBeneficiario ) checkNull(new CotOperaciones().modificarBeneficiario(param));
	}
}
