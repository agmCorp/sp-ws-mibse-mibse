package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class EnviarCotizacionFueraPautaSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(enviarCotizacionFueraPauta((ParamEnviarCotizacionFueraPauta) param));
	}

	private ResultEnviarCotizacionFueraPauta enviarCotizacionFueraPauta(ParamEnviarCotizacionFueraPauta myParam) {
		return new CotOperaciones().enviarCotizacionFueraPauta(myParam);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultEnviarCotizacionFueraPauta();
	}

}
