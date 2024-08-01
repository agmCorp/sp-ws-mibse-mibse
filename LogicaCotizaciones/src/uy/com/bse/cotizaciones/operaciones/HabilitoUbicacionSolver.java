package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class HabilitoUbicacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultHabilitoUbicacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return habilitoUbicacion((ParamHabilitoUbicacion) param);
	}

	private ResultHabilitoUbicacion  habilitoUbicacion(ParamHabilitoUbicacion param) {
		return (ResultHabilitoUbicacion ) checkNull(new CotOperaciones().habilitoUbicacion(param));
	}
}
