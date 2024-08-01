package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamMotivoEndoso;
import uy.com.bse.polizas.consultas.ResultMotivoEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaMotivosEndosoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaMotivosEndoso((ParamMotivoEndoso) param);
	}

	private ResultMotivoEndoso listaMotivosEndoso(ParamMotivoEndoso myParam) {
			return (ResultMotivoEndoso) checkNull(new ServiciosPolizasPersist().listaMotivosEndoso(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultMotivoEndoso();
	}

}
