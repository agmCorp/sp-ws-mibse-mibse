package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerCoberturasXBienSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerCoberturasXBien();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerCoberturasXBien( (ParamObtenerCoberturasXBien) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerCoberturasXBien();
		}
		return null;
	}
}
