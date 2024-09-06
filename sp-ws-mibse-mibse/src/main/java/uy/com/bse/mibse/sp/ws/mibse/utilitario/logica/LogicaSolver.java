package uy.com.bse.mibse.sp.ws.mibse.utilitario.logica;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

public interface LogicaSolver {
	ResultGenerico solve(ParamGenerico param);

	String getMyParamFullName();
}
