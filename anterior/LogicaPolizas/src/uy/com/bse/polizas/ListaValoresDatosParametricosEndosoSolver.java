package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamValoresDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ResultValoresDatosParametricosEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaValoresDatosParametricosEndosoSolver  extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaValoresDatosParametricos((ParamValoresDatosParametricosEndoso) param);
	}

	private ResultValoresDatosParametricosEndoso listaValoresDatosParametricos(ParamValoresDatosParametricosEndoso myParam) {
		return (ResultValoresDatosParametricosEndoso) checkNull( new ServiciosPolizasPersist().listaValoresDatosParametricos(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValoresDatosParametricosEndoso();
	}

}
