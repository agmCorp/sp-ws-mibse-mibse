package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoVigenciaTecnicaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoVigenciaTecnica ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoVigenciaTecnica((ParamHabilitoVigenciaTecnica) param);
	}

	private ResultHabilitoVigenciaTecnica  habilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param) {
		return (ResultHabilitoVigenciaTecnica ) checkNull(new CotOperaciones().habilitoVigenciaTecnica(param));
	}
}
