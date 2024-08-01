package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamObtenerValoresXDatoParametrico;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerValoresXDatoParametricoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerValoresXDatoParametrico( (ParamObtenerValoresXDatoParametrico) param);
	}

	private ResultCodiguera obtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico myParam  ) {
			return (ResultCodiguera) checkNull(new ServiciosPolizasPersist().obtenerValoresXDatoParametrico(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCodiguera();
	}

}
