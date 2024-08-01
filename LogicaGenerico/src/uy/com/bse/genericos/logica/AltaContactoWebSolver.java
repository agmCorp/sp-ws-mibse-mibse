package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ResultContactoWeb;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaContactoWebSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultContactoWeb();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaContactoWeb((ParamContactoWeb) param);
	}

	private ResultContactoWeb altaContactoWeb(ParamContactoWeb param) {
		return (ResultContactoWeb) checkNull(new ServiciosRectorPersist().altaContactoWeb(param));
	}
}
