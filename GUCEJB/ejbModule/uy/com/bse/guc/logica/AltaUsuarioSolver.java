package uy.com.bse.guc.logica;

import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class AltaUsuarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaUsuario();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(DataProviderUtil.getDataProvider().altaUsuario((ParamAltaUsuario) param));
	}

}
