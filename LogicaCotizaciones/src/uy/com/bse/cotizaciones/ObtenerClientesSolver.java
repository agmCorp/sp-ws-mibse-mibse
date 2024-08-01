package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamClientes;
import uy.com.bse.cotizaciones.consultas.ResultClientes;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerClientesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultClientes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerClientes((ParamClientes) param);
	}

	private ResultClientes  obtenerClientes(ParamClientes param) {
		return (ResultClientes ) checkNull(new CotizacionesPersist().obtenerClientes(param));
	}
}
