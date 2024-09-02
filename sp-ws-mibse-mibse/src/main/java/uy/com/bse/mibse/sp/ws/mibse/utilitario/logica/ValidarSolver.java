package uy.com.bse.mibse.sp.ws.mibse.utilitario.logica;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.AbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ResultValidar;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamValidar;

@Component
public final class ValidarSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidar();
	}

	// TODO aaguirre fixme
	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		//return checkNull(DataProviderUtil.getDataProvider().validar((ParamValidar) param));
		return null;
	}

	// TODO aaguirre revisar
	@Override
	public String getParameter() {
		return "ValidarSolver";
	}

		
	
}
