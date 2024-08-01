package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarComunicacionesClienteSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		ParamActualizarComunicacionesCliente comunicaciones = (ParamActualizarComunicacionesCliente) param;
		ServiciosMiBsePersist persistencia = new ServiciosMiBsePersist();
		ResultGenerico resultado=new ResultGenerico();
		if (comunicaciones.getComunicaciones()!=null&&!comunicaciones.getComunicaciones().isEmpty()) {
			for (DatosComunicacion dato : comunicaciones.getComunicaciones()) {
				ParamActualizarComunicacionCliente paramComunicacion = new ParamActualizarComunicacionCliente();
				paramComunicacion.setClave(comunicaciones.getClave());
				paramComunicacion.setUsuario(comunicaciones.getUsuario());
				paramComunicacion.setComunicacion(dato);
				ResultGenerico	res = checkNull(persistencia.actualizarComunicacionCliente(paramComunicacion));
				if(res.getHayError().booleanValue()){
					resultado.setError(res.getError());
					break;
				}
			}
		}
		return resultado;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

}
