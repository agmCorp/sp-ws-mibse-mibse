package uy.com.bse.cotizadorvya.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizadorVYA;
import uy.com.bse.cotizadorvya.persistencia.ServiciosCotizadorVYAPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPorcentajeAnticipoSolver extends XMLAbstractSolver {
	
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPorcentajeAnticipo();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosCotizadorVYAPersist().obtenerPorcentajeAnticipo((ParamPorcentajeAnticipo) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizadorVYA unParseo = new ParseoCotizadorVYA(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerPorcentajeAnticipo();
		}
		return null;
	}
}
