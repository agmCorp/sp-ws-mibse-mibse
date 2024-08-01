package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBancarios;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDatosBancariosSolver extends XMLAbstractSolver{
	
	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDatosBancarios();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerDatosBancarios((ParamObtenerDatosBancarios) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		 ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		 ResultObtenerDatosBancarios result =null;
		if (unParseo.generarDoc().booleanValue()) {
			result = unParseo.parsearObtenerDatosBancarios();
			if ("S".equalsIgnoreCase(result.getRequiereDatos())) {
				if (result.getDatosBanco()!=null &&result.getDatosBanco().isEmpty()) {
					result.setMensaje("No existen domicilios bancarios para los datos seleccionados.");
				}
			}
			return result;
		}
		return null;
	}

}
