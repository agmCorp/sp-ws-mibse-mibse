package uy.com.bse.cotizaciones.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotOperaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ActualizarPromocionVehiculoSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActualizarPromocionVehiculo();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotOperaciones().actualizarPromocionVehiculo((ParamActualizarPromocionVehiculo) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotOperaciones unParseo = new ParseoCotOperaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearActualizarPromocionVehiculo();
		}
		return null;
	}
}
