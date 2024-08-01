package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ResultTipoAcreedores;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoAcreedoresSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultTipoAcreedores ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoAcreedores((ParamTipoAcreedores) param);
	}

	private ResultTipoAcreedores  listaTipoAcreedores(ParamTipoAcreedores param) {
		return (ResultTipoAcreedores ) checkNull(new CotizacionesPersist().listaTipoAcreedores(param));
	}
}
