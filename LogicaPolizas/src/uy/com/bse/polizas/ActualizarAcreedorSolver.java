package uy.com.bse.polizas;

import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.recuotificacion.ParamAcreedorEndoso;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarAcreedorSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return actualizarAcreedor((ParamAcreedorEndoso) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

	private ResultGenerico actualizarAcreedor(ParamAcreedorEndoso param) {
		return (ResultGenerico) checkNull(new ServiciosPolizasPersist().actualizarAcreedor(param));
	}
}
