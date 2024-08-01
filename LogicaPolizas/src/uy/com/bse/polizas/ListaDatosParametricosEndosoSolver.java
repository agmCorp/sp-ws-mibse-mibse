package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ResultDatosParametricosEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaDatosParametricosEndosoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaDatosParametricosEndoso((ParamDatosParametricosEndoso) param) ;
	}

	private ResultDatosParametricosEndoso listaDatosParametricosEndoso(ParamDatosParametricosEndoso entrada) {
		return (ResultDatosParametricosEndoso) checkNull(new ServiciosPolizasPersist().listaDatosParametricosEndoso(entrada));
	}


	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosParametricosEndoso();
	}

}
