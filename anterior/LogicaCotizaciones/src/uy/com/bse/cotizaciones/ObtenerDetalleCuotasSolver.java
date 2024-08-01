package uy.com.bse.cotizaciones;

import java.util.ArrayList;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleCuota;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.polizas.entidades.DetalleCuota;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDetalleCuotasSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDetalleCuota();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerDetalleCuotas((ParamObtenerDetalleCuota) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			ResultObtenerDetalleCuota result = unParseo.parsearObtenerDetalleCuotas();
			if (result != null) {
				ArrayList<DetalleCuota> detalles = result.getCuotas();
				Double total = 0.0;
				for (int i = 0; i < detalles.size(); i++) {
					if (detalles.get(i) != null) {
						Double monto = detalles.get(i).getMontoCuota();
						total = total + monto;
					}
				}
				result.setTotal(total);
			}
			return result;
		}
		return null;
	}
}
