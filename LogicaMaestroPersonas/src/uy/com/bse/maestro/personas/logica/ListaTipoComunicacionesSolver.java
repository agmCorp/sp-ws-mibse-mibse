package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.interfaces.ParamTipoComunicaciones;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoComunicaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoComunicacionesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTipoComunicaciones();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaTipoComunicaciones((ParamTipoComunicaciones) param);
	}

	private ResultListaTipoComunicaciones listaTipoComunicaciones(ParamTipoComunicaciones param) {
		return (ResultListaTipoComunicaciones) checkNull(new ServiciosRectorPersist().listaTipoComunicaciones(param));
	}
}
