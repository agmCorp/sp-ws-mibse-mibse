package uy.com.bse.mibse.sp.ws.mibse.utilitario.logica;

import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamLogin;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamValidar;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ResultValidar;

@Component
public final class ValidarSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidar();
	}

	// TODO aaguirre fixme
	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		// return checkNull(DataProviderUtil.getDataProvider().validar((ParamValidar)
		// param));
		return null;
	}

	@Override
	public ParamGenerico getMyParamInstance() {
		return new ParamValidar();
	}
}
