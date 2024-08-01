package uy.com.bse.cotizaciones.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotOperaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class NuevaCotizacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultNuevaCotizacion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return nuevaCotizacion((ParamNuevaCotizacion) param);
	}

	private ResultNuevaCotizacion nuevaCotizacion(ParamNuevaCotizacion param) {
		return (ResultNuevaCotizacion) checkNull(parseValues(getXmlResult(param), param));
	}

	private ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotOperaciones().nuevaCotizacion((ParamNuevaCotizacion) param);
	}

	private ResultGenerico parseValues(ResultXmlPL xmlResult, ParamNuevaCotizacion param) {
		if (xmlResult.getHayError().booleanValue()) {
			ResultGenerico resultado = getMyResultInstance();
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(xmlResult.getError());
			return resultado;
		} else {
			ParseoCotOperaciones unParseo = new ParseoCotOperaciones(xmlResult.getXml());
			if (unParseo.generarDoc().booleanValue()) {
				ResultNuevaCotizacion resultado = unParseo.parsearNuevaCotizacion();
				if (resultado.getDatosCotizacion() != null) {
					resultado.getDatosCotizacion().setCodProducto(param.getCodProducto());
					resultado.getDatosCotizacion().setCodRamo(param.getCodRamo());
				}
				return resultado;
			} else {
				return null;
			}
		}
	}
}
