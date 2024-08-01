package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ResultGenerarEndoso;
import uy.com.bse.polizas.operaciones.ParamGenerarEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class GenerarEndosoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerarEndoso();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return generarEndoso((ParamGenerarEndoso) param);
	}

	private ResultGenerarEndoso generarEndoso(ParamGenerarEndoso param) {
		return new ServiciosPolizasPersist().generarEndoso(param);
	}
}
