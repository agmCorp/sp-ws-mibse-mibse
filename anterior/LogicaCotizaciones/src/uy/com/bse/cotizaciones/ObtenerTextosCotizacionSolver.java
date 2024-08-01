package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultTextosCotizacion;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerTextosCotizacionSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultTextosCotizacion();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerTextosCotizacion( (ParamTextosCotizacion) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerTextosCotizacion();
		}
		return null;
	}
}
