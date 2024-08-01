package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamProducto;
import uy.com.bse.cotizaciones.lovs.ResultListaProducto;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaProductoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaProducto ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaProducto((ParamProducto) param);
	}

	private ResultListaProducto  listaProducto(ParamProducto param) {
		return (ResultListaProducto ) checkNull(new CotizacionesPersist().listaProducto(param));
	}
}
