package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AnularCotizacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAnularCotizacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return anularCotizacion((ParamAnularCotizacion) param);
	}

	private ResultAnularCotizacion  anularCotizacion(ParamAnularCotizacion param) {
		return (ResultAnularCotizacion ) checkNull(new CotOperaciones().anularCotizacion(param));
	}
}
