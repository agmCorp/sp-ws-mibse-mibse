package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.parseo.ParseoMiBse;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPolizasFacturasPagasClienteSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPolizasFacturasPagasCliente();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosMiBsePersist().obtenerPolizasFacturasPagasCliente( (ParamObtenerPolizasFacturasPagasCliente) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoMiBse unParseo = new ParseoMiBse(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerPolizasFacturasPagasCliente();
		}
		return null;
	}
}
