package uy.com.bse.mibse.sp.ws.mibse.utilitario.logica;

import jakarta.validation.OverridesAttribute;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ResultLogin;

public final class LoginSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultLogin();
	}

	// TODO aaguirre FIXME !
	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return null;
	}
	/*@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(DataProviderUtil.getDataProvider().login((ParamLogin) param));
	}*/

	@Override
	// TODO aaguirre revisar
	public String getParameter() {
		return "";
	}

}
