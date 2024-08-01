package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ResultListaTiposFacturacion;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTiposFacturacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTiposFacturacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTiposFacturacion((ParamTiposFacturacion) param);
	}

	private ResultListaTiposFacturacion  listaTiposFacturacion(ParamTiposFacturacion param) {
		return (ResultListaTiposFacturacion ) checkNull(new CotizacionesPersist().listaTiposFacturacion(param));
	}
}
