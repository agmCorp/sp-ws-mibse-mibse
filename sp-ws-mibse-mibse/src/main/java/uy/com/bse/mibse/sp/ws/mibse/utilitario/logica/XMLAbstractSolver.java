package uy.com.bse.mibse.sp.ws.mibse.utilitario.logica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ServiciosError;

public abstract class XMLAbstractSolver extends AbstractSolver {

	private static final Logger LOGGER = LogManager.getLogger(XMLAbstractSolver.class);

	private ResultGenerico procesarErrores(ResultXmlPL xmlResult) {
		if (xmlResult.getHayError().booleanValue()) {
			ResultGenerico resultado = getMyResultInstance();
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(xmlResult.getError());
			return resultado;
		}
		return null;
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		ResultXmlPL xmlResult = getXmlResult(param);
		ResultGenerico result = procesarErrores(xmlResult);
		if (result != null) {
			LOGGER.debug("procesoLogica - database error: ", result.getError());
			if (result.getError()!=null && result.getError().getCodigo().intValue()<0) {
				LOGGER.debug("procesoLogica - database error y datos: ", result.getError());
				result=procesoResultadoConErrorYXml(result, xmlResult);
			}
		} else {
			result = checkNull(parseValues(xmlResult));
		}
		return result;
	}

	private ResultGenerico procesoResultadoConErrorYXml(ResultGenerico resultED, ResultXmlPL xmlResult) {
		 ServiciosError error = resultED.getError();
		 Boolean hayError= resultED.getHayError();
		 ResultGenerico result=checkNull(parseValues(xmlResult));
		 result.setHayError(hayError);
		 result.setError(error);
		return result;
	}

	protected abstract ResultXmlPL getXmlResult(ParamGenerico param);

	protected abstract ResultGenerico parseValues(ResultXmlPL xmlResult);
}
