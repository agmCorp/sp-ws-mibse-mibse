package uy.com.bse.guc.logica;

import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ResultLogin;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class LoginSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultLogin();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(DataProviderUtil.getDataProvider().login((ParamLogin) param));
	}

}
