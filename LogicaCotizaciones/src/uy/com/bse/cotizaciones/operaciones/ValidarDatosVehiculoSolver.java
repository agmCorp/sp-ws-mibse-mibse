package uy.com.bse.cotizaciones.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotOperaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ValidarDatosVehiculoSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidarDatosVehiculo();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotOperaciones().validarDatosVehiculo( (ParamValidarDatosVehiculo) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotOperaciones unParseo = new ParseoCotOperaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearValidarDatosVehiculo();
		}
		return null;
	}
}
