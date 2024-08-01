package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoListaBienesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoListaBienes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoListaBienes((ParamHabilitoListaBienes) param);
	}

	private ResultHabilitoListaBienes  habilitoListaBienes(ParamHabilitoListaBienes param) {
		return (ResultHabilitoListaBienes ) checkNull(new CotOperaciones().habilitoListaBienes(param));
	}
}
