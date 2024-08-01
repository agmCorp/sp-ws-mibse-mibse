package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamDatosParametricoPoliza;
import uy.com.bse.polizas.consultas.ResultDatosParametricoPoliza;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaDatosParametricosPolizaSolver extends AbstractSolver{

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaDatosParametricoPoliza((ParamDatosParametricoPoliza) param);
	}

	private ResultDatosParametricoPoliza listaDatosParametricoPoliza(ParamDatosParametricoPoliza myParam) {
		return (ResultDatosParametricoPoliza) checkNull(new ServiciosPolizasPersist().listaDatosParametricosPoliza(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosParametricoPoliza();
	}

}
