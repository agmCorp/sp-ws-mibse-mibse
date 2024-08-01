package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.LogicaSolver;

public class ObtenerResumenPlanesCoberturaSolver implements LogicaSolver {
	
	@Override
	public ResultGenerico solve(ParamGenerico param){
		ResultObtenerResumenPlanesCobertura salida = null;
		ParamObtenerResumenPlanesCobertura myParam = (ParamObtenerResumenPlanesCobertura)param;
		if(myParam!=null){
			try{
				salida = new ResultObtenerResumenPlanesCobertura();
				salida = obtenerResumenPlanesCobertura(myParam);
			}catch(Exception e){
				salida.setHayError(Boolean.TRUE);
				salida.setError(new ServiciosError(32, "No se pudo obtener el resumen de los planes"));
			}
		}
		return salida;
	}

	
	private ResultObtenerResumenPlanesCobertura obtenerResumenPlanesCobertura(ParamObtenerResumenPlanesCobertura param) {
		ResultObtenerResumenPlanesCobertura result = new ResultObtenerResumenPlanesCobertura();
		CotizacionesPersist persistencia = new CotizacionesPersist();
		
		ResultXmlPL xmlResult = persistencia.obtenerResumenPlanesCobertura(param);
		if (xmlResult.getHayError().booleanValue()) {				
			result.setHayError(Boolean.TRUE);
			result.setError(xmlResult.getError());
		}else{
			 ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
			if (unParseo.generarDoc().booleanValue()) {
				result = unParseo.parsearObtenerResumenPlanesCobertura();
			}
		}
		return result;
	}
}
