package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoBotonesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoBotones ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoBotones((ParamHabilitoBotones) param);
	}

	private ResultHabilitoBotones  habilitoBotones(ParamHabilitoBotones param) {
		return (ResultHabilitoBotones ) checkNull(new CotOperaciones().habilitoBotones(param));
	}
}
