package uy.com.bse.consultas;

import uy.com.bse.consultas.operaciones.ParamActualizarPolizaFlotante;
import uy.com.bse.consultas.persistencia.ConsultasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarPolizaFlotanteSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}
	

	private ResultGenerico  actualizarPolizaFlotante(ParamActualizarPolizaFlotante param ) {
		return (ResultGenerico ) checkNull(new ConsultasPersist().actualizarPolizaFlotante(param));
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return actualizarPolizaFlotante((ParamActualizarPolizaFlotante) param);
	}

	
}
