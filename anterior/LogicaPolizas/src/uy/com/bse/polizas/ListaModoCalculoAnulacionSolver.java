package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamModoCalculo;
import uy.com.bse.polizas.consultas.ResultModoCalculo;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaModoCalculoAnulacionSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaModoCalculoAnulacion((ParamModoCalculo) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModoCalculo();
	}
	
	private ResultModoCalculo listaModoCalculoAnulacion(ParamModoCalculo param) {
		return (ResultModoCalculo) checkNull(new ServiciosPolizasPersist().listaModoCalculoAnulacion(param));
	}

}
