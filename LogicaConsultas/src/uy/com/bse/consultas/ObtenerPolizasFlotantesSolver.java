package uy.com.bse.consultas;

import uy.bse.rector.servicios.parseo.ParseoConsultas;
import uy.com.bse.consultas.operaciones.ParamObtenerPolizasFlotantes;
import uy.com.bse.consultas.operaciones.ResultObtenerPolizasFlotantes;
import uy.com.bse.consultas.persistencia.ConsultasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPolizasFlotantesSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPolizasFlotantes();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ConsultasPersist().obtenerPolizasFlotantes((ParamObtenerPolizasFlotantes) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoConsultas unParseo = new ParseoConsultas(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerPolizasFlotantes();
		}
		return null;
	}

}
