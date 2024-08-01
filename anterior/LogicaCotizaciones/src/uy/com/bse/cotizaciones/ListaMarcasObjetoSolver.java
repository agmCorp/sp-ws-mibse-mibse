package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ResultListaMarcasObjeto;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaMarcasObjetoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaMarcasObjeto ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaMarcasObjeto((ParamMarcasObjeto) param);
	}

	private ResultListaMarcasObjeto  listaMarcasObjeto(ParamMarcasObjeto param) {
		return (ResultListaMarcasObjeto ) checkNull(new CotizacionesPersist().listaMarcasObjeto(param));
	}
}
