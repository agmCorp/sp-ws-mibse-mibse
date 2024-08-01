package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarListaBienesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificarListaBienes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return modificarListaBienes((ParamModificarListaBienes) param);
	}

	private ResultModificarListaBienes  modificarListaBienes(ParamModificarListaBienes param) {
		return (ResultModificarListaBienes ) checkNull(new CotOperaciones().modificarListaBienes(param));
	}
}
