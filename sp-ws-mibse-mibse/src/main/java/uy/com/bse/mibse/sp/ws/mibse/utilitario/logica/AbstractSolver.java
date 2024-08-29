package uy.com.bse.mibse.sp.ws.mibse.utilitario.logica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.*;


public abstract class AbstractSolver implements LogicaSolver {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractSolver.class);

    @Override
	public ResultGenerico solve(ParamGenerico param) {
		ResultGenerico result = chequeoPreCondiciones(param);
		if (result != null) {
			result = procesoLogica(param);
			procesoPostCondiciones(param, result);
		}
		return result;
	}

	protected ResultGenerico chequeoPreCondiciones(ParamGenerico param) {
		ResultGenerico salida = null;
		salida = getMyResultInstance();
		return salida;
	}

	protected abstract ResultGenerico getMyResultInstance();

	protected abstract ResultGenerico procesoLogica(ParamGenerico param);

	protected void procesoPostCondiciones(ParamGenerico param, ResultGenerico result) {
		LOG.debug("procesoPostCondiciones fin, result: "+result);
	}
	
	protected ResultGenerico checkNull(ResultGenerico resultado) {
		if (resultado == null) {
			LOG.debug("procesoLogica - retornando objeto vacio ");
			resultado = getMyResultInstance();
			resultado.setError(ErrorResolver.getError(Values.PERSEXCEPTCOD));
			resultado.setHayError(Boolean.TRUE);
		}
		return resultado;
	}





}
