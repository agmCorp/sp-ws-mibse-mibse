package uy.com.bse.cotizaciones.pymes;

import uy.bse.rector.servicios.parseo.ParseoCotizadorPymes;
import uy.com.bse.cotizadores.pymes.ParamCalcularPymes;
import uy.com.bse.cotizadores.pymes.ResultCalcularPymes;
import uy.com.bse.cotizadorpymes.persistencia.ServiciosCotizadorPymesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class CalcularPymesSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCalcularPymes();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosCotizadorPymesPersist().calcularPymes((ParamCalcularPymes) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizadorPymes unParseo = new ParseoCotizadorPymes(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearCalcularPymes();
		}
		return null;
	}

}
