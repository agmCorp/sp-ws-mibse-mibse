package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamObtenerSiniestros;
import uy.com.bse.polizas.consultas.ResultObtenerSiniestros;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerSiniestrosSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return obtenerSiniestros((ParamObtenerSiniestros) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerSiniestros();
	}

	private ResultObtenerSiniestros obtenerSiniestros(ParamObtenerSiniestros param) {
		return (ResultObtenerSiniestros) checkNull( new ServiciosPolizasPersist().obtenerSiniestros(param));
	}
}
