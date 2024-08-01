package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaListaBienesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaListaBienes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return altaListaBienes((ParamAltaListaBienes) param);
	}

	private ResultAltaListaBienes  altaListaBienes(ParamAltaListaBienes param) {
		return (ResultAltaListaBienes ) checkNull(new CotOperaciones().altaListaBienes(param));
	}
}
