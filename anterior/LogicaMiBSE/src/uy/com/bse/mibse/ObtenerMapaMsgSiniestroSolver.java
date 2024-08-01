package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.parseo.ParseoMiBse;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerMapaMsgSiniestroSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerMapaMsgSiniestro();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosMiBsePersist().obtenerMapaMsgSiniestro((ParamObtenerMapaMsgSiniestro) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		if (xmlResult.getXml() != null) {
			ParseoMiBse unParseo = new ParseoMiBse(xmlResult.getXml());
			if (unParseo.generarDoc().booleanValue()) {
				return unParseo.parsearObtenerMapaMsgSiniestro();
			}
		}
		ResultObtenerMapaMsgSiniestro r = new ResultObtenerMapaMsgSiniestro();
		r.setHayError(xmlResult.getHayError());
		r.setError(xmlResult.getError());
		return r;////
	}

}
