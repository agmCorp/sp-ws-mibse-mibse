package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoAgregarBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoAgregarBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoAgregarBien((ParamHabilitoAgregarBien) param);
	}

	private ResultHabilitoAgregarBien  habilitoAgregarBien(ParamHabilitoAgregarBien param) {
		return (ResultHabilitoAgregarBien ) checkNull(new CotOperaciones().habilitoAgregarBien(param));
	}
}
