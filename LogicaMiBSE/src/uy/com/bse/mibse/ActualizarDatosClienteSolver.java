package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarDatosClienteSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		ServiciosMiBsePersist persistencia = new ServiciosMiBsePersist();
		ResultGenerico resultado=new ResultGenerico();
			ParamActualizarDatosCliente datos	=(ParamActualizarDatosCliente) param;
			if (datos.getComunicaciones()!=null &&!datos.getComunicaciones().isEmpty()) {
				for (DatosComunicacion dato : datos.getComunicaciones()) {
					ParamActualizarComunicacionCliente paramComunicacion = new ParamActualizarComunicacionCliente();
					paramComunicacion.setClave(datos.getClave());
					paramComunicacion.setUsuario(datos.getUsuario());
					paramComunicacion.setComunicacion(dato);
					ResultGenerico	res = checkNull(persistencia.actualizarComunicacionCliente(paramComunicacion));
					if(res.getHayError().booleanValue()){
						resultado.setError(res.getError());
						break;
					}
				}
			}
			
		if (!resultado.getHayError().booleanValue()) {
			if (datos.getTipoDocumento()!=null && !datos.getTipoDocumento().equalsIgnoreCase("RUT")) {
				resultado = checkNull(persistencia.actualizarDatosCliente(datos));
			}
		}
		return resultado;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

}
