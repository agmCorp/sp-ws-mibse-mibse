package uy.com.bse.cotizaciones;

import uy.bse.rector.servicios.parseo.ParseoCotizaciones;
import uy.com.bse.cotizaciones.lovs.ParamValoresTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultValoresTipoNota;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaValoresTipoNotaSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		ResultValoresTipoNota result = null;
		ParamValoresTipoNota myParam = (ParamValoresTipoNota)param;
		if(myParam!=null){
			try{
				result = new ResultValoresTipoNota();
				result = listaValoresTipoNota(myParam);
			}catch(Exception e){
				result.setHayError(Boolean.TRUE);
				result.setError(new ServiciosError(31, "No se pudieron obtener los valores del segundo campo de las notas"));				
			}				
		}
		return result;
	}

	private ResultValoresTipoNota listaValoresTipoNota(ParamValoresTipoNota param) {
		ResultValoresTipoNota result = new ResultValoresTipoNota();
		CotizacionesPersist persistencia = new CotizacionesPersist();

		ResultXmlPL xmlResult = persistencia.listaValoresTipoNota(param);
		if (xmlResult.getHayError().booleanValue()) {				
			result.setHayError(Boolean.TRUE);
			result.setError(xmlResult.getError());
		}else{
			 ParseoCotizaciones unParseo = new ParseoCotizaciones(xmlResult.getXml());
			if (unParseo.generarDoc().booleanValue()) {
				result = unParseo.parsearListaValoresTipoNota();
			}
		}
		
		
		return result;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValoresTipoNota();
	}
	

}
