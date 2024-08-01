package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamMotivosBaja;
import uy.com.bse.maestro.personas.interfaces.ResultListaMotivosBaja;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaMotivosBajaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaMotivosBaja();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaMotivosBaja((ParamMotivosBaja) param);
	}

	private ResultListaMotivosBaja listaMotivosBaja(ParamMotivosBaja param) {
		return (ResultListaMotivosBaja) checkNull(new ServiciosRectorPersist().listaMotivosBaja(param));
	}
}
