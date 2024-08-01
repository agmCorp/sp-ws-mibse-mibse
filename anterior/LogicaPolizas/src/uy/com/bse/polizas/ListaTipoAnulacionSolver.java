package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamTipoAnulacion;
import uy.com.bse.polizas.consultas.ResultTipoAnulacion;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoAnulacionSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoAnulacion((ParamTipoAnulacion) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultTipoAnulacion();
	}
	
	private ResultTipoAnulacion listaTipoAnulacion(ParamTipoAnulacion param) {
		return (ResultTipoAnulacion) checkNull(new ServiciosPolizasPersist().listaTipoAnulacion(param));
			
	}

}
