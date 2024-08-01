package uy.com.bse.cotizaciones.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ConfigurarFechasPromocionSolver extends XMLAbstractSolver {

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotOperaciones().configurarFechasPromocion((ParamConfigurarFechasPromocion) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearConfigurarFechasPromocion();
		}
		return null;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultConfigurarFechasPromocion();
	}

}
