package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class GuardarAseguradoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return checkNull(guardarAsegurado((ParamGuardarAsegurado) param));
	}

	private ResultGuardarAsegurado guardarAsegurado(ParamGuardarAsegurado myParam) {
		return new CotOperaciones().guardarAsegurado(myParam);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGuardarAsegurado();
	}

}
