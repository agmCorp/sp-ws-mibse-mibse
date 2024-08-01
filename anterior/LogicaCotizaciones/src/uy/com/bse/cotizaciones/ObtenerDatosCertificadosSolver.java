package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ResultDatosCertificados;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDatosCertificadosSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosCertificados();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerDatosCertificados( (ParamDatosCertificados) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearDatosCertificados();
		}
		return null;
	}
}
