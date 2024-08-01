package uy.com.bse.polizas;

import uy.bse.rector.servicios.parseo.ParseoPolizas;
import uy.com.bse.polizas.consultas.ParamGuardarImprimirReporte;
import uy.com.bse.polizas.consultas.ResultGuardarImprimirReporte;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class GuardarImprimirReporteSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGuardarImprimirReporte();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosPolizasPersist().guardarImprimirReporte((ParamGuardarImprimirReporte) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoPolizas unParseo = new ParseoPolizas(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearGuardarImprimirReporte();
		}
		return null;
	}
}
