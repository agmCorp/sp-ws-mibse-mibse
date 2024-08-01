package uy.com.bse.polizas;

import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.recuotificacion.ParamActualizarDatosBancariosEndoso;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarDatosBancariosEndosoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return actualizarDatosBancariosEndoso((ParamActualizarDatosBancariosEndoso) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

	private ResultGenerico actualizarDatosBancariosEndoso(ParamActualizarDatosBancariosEndoso param) {
		return (ResultGenerico) checkNull(new ServiciosPolizasPersist().actualizarDatosBancariosEndoso(param));
	}
}
