package uy.com.bse.polizas;

import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.recuotificacion.ParamOrigenEndoso;
import uy.com.bse.recuotificacion.ResultOrigenEndoso;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaOrigenEndosoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaOrigenEndoso((ParamOrigenEndoso) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultOrigenEndoso();
	}
	
	private ResultOrigenEndoso listaOrigenEndoso(ParamOrigenEndoso param) {
		return (ResultOrigenEndoso) checkNull(new ServiciosPolizasPersist().listaOrigenEndoso(param));
	}
}
