package uy.com.bse.cotizaciones;

import java.util.ArrayList;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPlanesCoberturaSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPlanesCobertura();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerPlanesCobertura((ParamObtenerPlanesCobertura) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ResultObtenerPlanesCobertura result = null;
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			result = unParseo.parsearObtenerPlanesCobertura();
			if (result != null) {
				ArrayList<Certificado> certificados = result.getListaCertificados();
				ArrayList<String> titulosDistintos = new ArrayList<String>();
				titulosDistintos.add("");
				if (certificados != null) {
					for (int j = 0; j < certificados.size(); j++) {
						ArrayList<PlanCobertura> planesCob = certificados.get(j).getListaPlanesCobertura();
						if (planesCob != null) {
							for (int k = 0; k < planesCob.size(); k++) {
								ArrayList<PlanPago> planesCert = planesCob.get(k).getPlanesPago();
								if (planesCert != null) {
									for (int l = 0; l < planesCert.size(); l++) {
										String desc = planesCert.get(l).getDescripcion();
										if (!titulosDistintos.contains(desc)) {
											titulosDistintos.add(desc);
										}
									}
								}
							}
						}
					}
				}
				result.setTitulos(titulosDistintos);
			}
		}
		return result;
	}

}
