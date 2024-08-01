package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamMotivoAbandono;
import uy.com.bse.polizas.consultas.ResultMotivoAbandono;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaMotivoAbandonoSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaMotivoAbandono((ParamMotivoAbandono) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultMotivoAbandono();
	}

	private ResultMotivoAbandono listaMotivoAbandono(ParamMotivoAbandono param) {
		return (ResultMotivoAbandono) checkNull(new ServiciosPolizasPersist().listaMotivoAbandono(param));
	}
}
