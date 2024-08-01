package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamObtenerDetalleTextoPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDetalleTextoPoliza;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDetalleTextoPolizaSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerDetalleTextoPoliza((ParamObtenerDetalleTextoPoliza) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDetalleTextoPoliza();
	}

	private ResultObtenerDetalleTextoPoliza obtenerDetalleTextoPoliza(ParamObtenerDetalleTextoPoliza param) {
		return (ResultObtenerDetalleTextoPoliza) checkNull( new ServiciosPolizasPersist().obtenerDetalleTextoPoliza(param));
	}

}
