package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultCotizaciones;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerCotizacionesSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCotizaciones();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerCotizaciones( (ParamCotizaciones) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerCotizaciones();
		}
		return null;
	}
}
