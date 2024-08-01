package uy.com.bse.polizas;

import uy.bse.rector.servicios.parseo.ParseoPolizas;
import uy.com.bse.polizas.consultas.ParamObtenerPolizasCabezal;
import uy.com.bse.polizas.consultas.ResultObtenerPolizasCabezal;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPolizasCabezalSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPolizasCabezal();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosPolizasPersist().obtenerPolizasCabezal((ParamObtenerPolizasCabezal) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoPolizas unParseo = new ParseoPolizas(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerPolizasCabezal();
		}
		return null;
	}
}
