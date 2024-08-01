package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarDatosBancariosSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return checkNull(actualizarDatosBancarios((ParamActualizarDatosBancarios) param));
	}

	private ResultActualizarDatosBancarios actualizarDatosBancarios(ParamActualizarDatosBancarios myParam) {
		CotOperaciones persistencia = new CotOperaciones();
		ResultActualizarDatosBancarios result =  persistencia.actualizarDatosBancarios(myParam);
		return result;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActualizarDatosBancarios();
	}

}
