package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BajaListaBienesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultBajaListaBienes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return bajaListaBienes((ParamBajaListaBienes) param);
	}

	private ResultBajaListaBienes  bajaListaBienes(ParamBajaListaBienes param) {
		return (ResultBajaListaBienes ) checkNull(new CotOperaciones().bajaListaBienes(param));
	}
}
