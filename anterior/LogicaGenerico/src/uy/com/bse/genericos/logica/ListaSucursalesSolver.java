package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ResultListaSucursales;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaSucursalesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaSucursales ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaSucursales((ParamListaSucursales) param);
	}

	private ResultListaSucursales  listaSucursales(ParamListaSucursales param) {
		return (ResultListaSucursales ) checkNull(new ServiciosRectorPersist().listaSucursales(param));
	}
}
