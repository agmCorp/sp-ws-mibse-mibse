package uy.com.bse.utilitario.logica;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;

public abstract class AbstractSolver implements LogicaSolver {

	private static final Logger LOG = LogManager.getLogger(AbstractSolver.class);	

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
