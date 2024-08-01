package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamAcreedoresFiltrados;
import uy.com.bse.cotizaciones.lovs.ResultAcreedor;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaAcreedoresFiltradosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAcreedor ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaAcreedoresFiltrados((ParamAcreedoresFiltrados) param);
	}

	private ResultAcreedor  listaAcreedoresFiltrados(ParamAcreedoresFiltrados param) {
		return (ResultAcreedor ) checkNull(new CotizacionesPersist().listaAcreedoresFiltrados(param));
	}
}
