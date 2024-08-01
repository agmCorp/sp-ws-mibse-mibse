package uy.com.bse.polizas;

import uy.bse.rector.servicios.parseo.ParseoPolizas;
import uy.com.bse.polizas.consultas.ParamObtenerDatosFacturaDigital;
import uy.com.bse.polizas.consultas.ResultObtenerDatosFacturaDigital;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDatosFacturaDigitalSolver extends XMLAbstractSolver {

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosPolizasPersist().obtenerDatosFacturaDigital((ParamObtenerDatosFacturaDigital) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoPolizas unParseo = new ParseoPolizas(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerDatosFacturaDigital();
		}
		return null;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDatosFacturaDigital();
	}
}
