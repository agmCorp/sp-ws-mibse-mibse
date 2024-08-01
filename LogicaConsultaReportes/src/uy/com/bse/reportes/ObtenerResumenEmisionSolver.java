package uy.com.bse.reportes;

import uy.bse.rector.servicios.parseo.ParseoConsultasReportes;
import uy.com.bse.reportes.persistencia.ConsultaReportesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;


public class ObtenerResumenEmisionSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerResumenEmision();
	}
	

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ConsultaReportesPersist().obtenerResumenEmision((ParamObtenerResumenEmision) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoConsultasReportes unParseo = new ParseoConsultasReportes(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerResumenEmision();
		}
		return null;
	}

	
}
