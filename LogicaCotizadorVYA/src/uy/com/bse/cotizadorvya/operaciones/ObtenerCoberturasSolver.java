package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.cotizadorvya.persistencia.ServiciosCotizadorVYAPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerCoberturasSolver  extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerCoberturas();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerCoberturas((ParamObtenerCoberturas) param);
	}

	private ResultObtenerCoberturas obtenerCoberturas(ParamObtenerCoberturas param) {
		return (ResultObtenerCoberturas) checkNull(new ServiciosCotizadorVYAPersist().obtenerCoberturas(param));
	}
}
