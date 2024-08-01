package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.operaciones.CotOperaciones;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCertificadoCero;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarCertificadoCero;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarCertificadoCeroSolver extends AbstractSolver{

	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
     return actualizarCertificadoCero((ParamActualizarCertificadoCero) param);
	}

	private ResultActualizarCertificadoCero actualizarCertificadoCero(ParamActualizarCertificadoCero myParam) {
		return (ResultActualizarCertificadoCero) checkNull(new CotOperaciones().actualizarCertificadoCero(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActualizarCertificadoCero();
	}
}
