package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class ActualizarDireccionesCotizacionSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(new CotOperaciones().actualizarDireccionesCotizacion((ParamActualizarDirCotizacion) param));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActualizarDirCotizacion();
	}

}
