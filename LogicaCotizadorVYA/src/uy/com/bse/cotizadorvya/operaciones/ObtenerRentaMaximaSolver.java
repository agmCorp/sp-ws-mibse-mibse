package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.cotizadorvya.persistencia.ServiciosCotizadorVYAPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerRentaMaximaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultRentaMaxima();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerRentaMaxima((ParamRentaMaxima) param);
	}

	private ResultRentaMaxima obtenerRentaMaxima(ParamRentaMaxima param) {
		return (ResultRentaMaxima) checkNull(new ServiciosCotizadorVYAPersist().obtenerRentaMaxima(param));
	}
}
