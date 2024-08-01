package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamTipoUsuario;
import uy.com.bse.servicios.rector.interfaces.ResultTipoUsuario;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class TipoUsuarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultTipoUsuario();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return tipoUsuario((ParamTipoUsuario) param);
	}

	private ResultTipoUsuario tipoUsuario(ParamTipoUsuario param) {
		return (ResultTipoUsuario) checkNull(new ServiciosRectorPersist().tipoUsuario(param));
	}
}
