package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ResultListaTodosProductos;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTodosProductoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaTodosProductos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTodosProducto((ParamListaTodosProductos) param);
	}

	private ResultListaTodosProductos  listaTodosProducto(ParamListaTodosProductos param) {
		return (ResultListaTodosProductos ) checkNull(new CotizacionesPersist().listaTodosProductos(param));
	}
}
