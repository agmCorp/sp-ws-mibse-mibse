package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class CopiarCotizaPoliSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCopiarCotizaPoli ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return copiarCotizaPoli((ParamCopiarCotizaPoli) param);
	}

	private ResultCopiarCotizaPoli  copiarCotizaPoli(ParamCopiarCotizaPoli param) {
		return (ResultCopiarCotizaPoli ) checkNull(new CotOperaciones().copiarCotizaPoli(param));
	}
}
