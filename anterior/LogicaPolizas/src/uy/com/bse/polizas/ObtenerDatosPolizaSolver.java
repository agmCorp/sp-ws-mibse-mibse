package uy.com.bse.polizas;

import uy.bse.rector.servicios.parseo.ParseoPolizas;
import uy.com.bse.polizas.consultas.ParamObtenerDatosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDatosPoliza;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDatosPolizaSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDatosPoliza();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosPolizasPersist().obtenerDatosPoliza((ParamObtenerDatosPoliza) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoPolizas unParseo = new ParseoPolizas(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerDatosPoliza();
		}
		return null;
	}
}
