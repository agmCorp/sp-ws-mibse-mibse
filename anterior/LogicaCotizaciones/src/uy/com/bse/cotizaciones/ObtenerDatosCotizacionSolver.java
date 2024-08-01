package uy.com.bse.cotizaciones;

import java.util.ArrayList;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamDatoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultDatosCotizacion;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.cotizaciones.operaciones.PlanPagoCotizacion;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDatosCotizacionSolver extends XMLAbstractSolver{

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosCotizacion();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotizacionesPersist().obtenerDatosCotizacion( (ParamDatoCotizacion) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			ResultDatosCotizacion resultado = unParseo.parsearDatosCotizacion();
			if(resultado!=null){					
				ArrayList<PlanPagoCotizacion> planesPagoCoti = resultado.getPlanesPago();										
				ArrayList<String> titulosDistintos = new ArrayList<String>();
				titulosDistintos.add("");
				
				if(planesPagoCoti!=null){
					for (int j = 0; j < planesPagoCoti.size(); j++) {
						ArrayList<Certificado> certificados = planesPagoCoti.get(j).getCertificados();						
						if(certificados!=null){
							for (int k = 0; k < certificados.size(); k++) {
								ArrayList<PlanCobertura> planesCob = certificados.get(k).getListaPlanesCobertura();										
								if(planesCob!=null){
									for (int l = 0; l < planesCob.size(); l++) {
										ArrayList<PlanPago> planesCert = planesCob.get(l).getPlanesPago();
										if(planesCert!=null){
											for(int m = 0; m<planesCert.size(); m++){
												String desc = planesCert.get(m).getDescripcion();
												if(!titulosDistintos.contains(desc)){
													titulosDistintos.add(desc);
												}
											}
										}
										
									}
								}
							}
						}
					}
				}

				resultado.setTitulos(titulosDistintos);
			}
			return resultado;
		}
		return null;
	}
}
