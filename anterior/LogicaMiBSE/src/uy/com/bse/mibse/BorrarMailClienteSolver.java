package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BorrarMailClienteSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		ParamBorrarMailCliente comunicacion = (ParamBorrarMailCliente) param;
		ServiciosMiBsePersist persistencia = new ServiciosMiBsePersist();
		ResultGenerico resultado = new ResultGenerico();
		if (comunicacion.getComunicacion() != null) {
			ParamBorrarMailCliente paramComunicacion = new ParamBorrarMailCliente();
			paramComunicacion = comunicacion;
			ResultGenerico res = checkNull(persistencia.borrarMailCliente(paramComunicacion));
			if(res.getHayError().booleanValue()){
				resultado.setError(res.getError());
				resultado.setHayError(Boolean.TRUE);
			}
		}
		return resultado;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

}
