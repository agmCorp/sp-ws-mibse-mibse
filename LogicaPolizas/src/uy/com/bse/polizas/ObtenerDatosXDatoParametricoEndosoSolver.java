package uy.com.bse.polizas;

import uy.bse.rector.servicios.parseo.ParseoPolizas;
import uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ResultDatosXDatosParametricosEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDatosXDatoParametricoEndosoSolver extends XMLAbstractSolver{
	
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosXDatosParametricosEndoso();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosPolizasPersist().obtenerDatosXDatoParametricoEndoso((ParamDatosXDatosParametricosEndoso) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoPolizas unParseo = new ParseoPolizas(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerDatosXDatoParametricoEndoso();
		}
		return null;
	}

}
