package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamObtenerDatosParametricos;
import uy.com.bse.polizas.consultas.ResultObtenerDatosParametricos;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDatosParametricosSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerDatosParametricos((ParamObtenerDatosParametricos) param);
	}

	private ResultObtenerDatosParametricos obtenerDatosParametricos(ParamObtenerDatosParametricos myParam ) {
			return (ResultObtenerDatosParametricos) checkNull(new ServiciosPolizasPersist().obtenerDatosParametricos(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDatosParametricos();
	}

}
