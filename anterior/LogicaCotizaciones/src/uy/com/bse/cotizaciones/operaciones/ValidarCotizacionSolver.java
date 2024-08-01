package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ValidarCotizacionSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(validarCotizacion((ParamValidarCotizacion) param));
	}

	private ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion myParam) {
		return new CotOperaciones().validarCotizacion(myParam);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidarCotizacion();
	}

}
