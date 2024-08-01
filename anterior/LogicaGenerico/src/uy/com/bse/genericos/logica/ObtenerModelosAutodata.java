package uy.com.bse.genericos.logica;

import uy.bse.rector.servicios.parseo.ParseoObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.autodata.operaciones.ResultObtenerModelosAutodata;
import uy.com.bse.autodata.persistencia.ServiciosAutodataPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerModelosAutodata extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerModelosAutodata();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosAutodataPersist().obtenerModelosAutodata((ParamObtenerModelosAutodata) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoObtenerDatosAuto unParseo = new ParseoObtenerDatosAuto(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerModelosAutodata();
		}
		return null;
	}
}
