package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamValoresDatosParametricosPoliza;
import uy.com.bse.polizas.consultas.ResultValoresDatosParametricosPoliza;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaValoresDatosParametricosPolizaSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaValoresDatosParametricosPoliza((ParamValoresDatosParametricosPoliza) param);
	}

	private ResultValoresDatosParametricosPoliza listaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza myParam) {
		return (ResultValoresDatosParametricosPoliza) checkNull( new ServiciosPolizasPersist().listaValoresDatosParametricosPoliza(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValoresDatosParametricosPoliza();
	}
		
}
