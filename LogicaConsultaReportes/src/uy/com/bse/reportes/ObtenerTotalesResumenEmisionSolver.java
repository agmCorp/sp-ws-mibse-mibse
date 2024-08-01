package uy.com.bse.reportes;

import uy.bse.rector.servicios.parseo.ParseoConsultasReportes;
import uy.com.bse.reportes.persistencia.ConsultaReportesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerTotalesResumenEmisionSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerTotalesResumenEmision();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ConsultaReportesPersist().obtenerTotalesResumenEmision((ParamObtenerTotalesResumenEmision) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoConsultasReportes unParseo = new ParseoConsultasReportes(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerTotalesResumenEmision();
		}
		return null;
	}

}
