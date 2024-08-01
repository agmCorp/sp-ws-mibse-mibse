package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoQuitarBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoQuitarBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoQuitarBien((ParamHabilitoQuitarBien) param);
	}

	private ResultHabilitoQuitarBien  habilitoQuitarBien(ParamHabilitoQuitarBien param) {
		return (ResultHabilitoQuitarBien ) checkNull(new CotOperaciones().habilitoQuitarBien(param));
	}
}
