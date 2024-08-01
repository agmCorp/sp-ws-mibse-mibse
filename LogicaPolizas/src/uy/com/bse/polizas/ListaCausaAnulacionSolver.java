package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamCausaAnulacion;
import uy.com.bse.polizas.consultas.ResultCausaAnulacion;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaCausaAnulacionSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaCausaAnulacion((ParamCausaAnulacion) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCausaAnulacion();
	}

	private ResultCausaAnulacion listaCausaAnulacion(ParamCausaAnulacion param) {
		return (ResultCausaAnulacion) checkNull(new ServiciosPolizasPersist().listaCausaAnulacion(param));
	}
}
