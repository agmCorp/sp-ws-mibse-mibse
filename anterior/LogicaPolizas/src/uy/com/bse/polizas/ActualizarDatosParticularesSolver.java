package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ResultActualizarDatosParticulares;
import uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarDatosParticularesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActualizarDatosParticulares();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return actualizarDatosParticulares((ParamActualizarDatosParticulares) param);
	}

	private ResultActualizarDatosParticulares actualizarDatosParticulares(ParamActualizarDatosParticulares param) {
		return (ResultActualizarDatosParticulares) checkNull(new ServiciosPolizasPersist().actualizarDatosParticulares( param));
	}
	
}
