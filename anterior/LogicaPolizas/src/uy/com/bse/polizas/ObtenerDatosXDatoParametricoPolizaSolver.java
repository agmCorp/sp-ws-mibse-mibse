package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosPoliza;
import uy.com.bse.polizas.consultas.ResultDatosXDatosParametricosPoliza;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerDatosXDatoParametricoPolizaSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerDatosXDatoParametricoPoliza((ParamDatosXDatosParametricosPoliza) param);
	}

	private ResultDatosXDatosParametricosPoliza obtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza myParam) {
		return (ResultDatosXDatosParametricosPoliza) checkNull( new ServiciosPolizasPersist().obtenerDatosXDatoParametricoPoliza(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosXDatosParametricosPoliza();
	}

}
