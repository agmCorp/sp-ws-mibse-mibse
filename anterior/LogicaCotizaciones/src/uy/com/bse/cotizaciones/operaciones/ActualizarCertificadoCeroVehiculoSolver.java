package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarCertificadoCeroVehiculoSolver extends AbstractSolver{

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
     return checkNull(new CotOperaciones().actualizarCertificadoCeroVehiculo((ParamActualizarCertificadoCeroVehiculo) param));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}
}
