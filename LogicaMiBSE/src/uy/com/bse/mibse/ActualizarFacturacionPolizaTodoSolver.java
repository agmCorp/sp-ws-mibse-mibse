package uy.com.bse.mibse;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ActualizarFacturacionPolizaTodoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		
		ResultGenerico resultado=new ResultGenerico();
		ParamActualizarFacturacionPolizaTodo datos	=(ParamActualizarFacturacionPolizaTodo) param;
		if (datos.getDatosFacturacions()!=null &&!datos.getDatosFacturacions().isEmpty()) {
			ServiciosMiBsePersist persistencia = new ServiciosMiBsePersist();
			for (DatosFacturacion dato : datos.getDatosFacturacions()) {
				ParamActualizarFacturacionPoliza paramFact = new ParamActualizarFacturacionPoliza();
				paramFact.setClave(datos.getClave());
				paramFact.setUsuario(datos.getUsuario());
				paramFact.setCodMarca((dato.getEsFactElectronica()));
				paramFact.setNumPoliza(dato.getNroPoliza());
				paramFact.setCodRamo(dato.getCodRamo());
				ResultGenerico	res = checkNull(persistencia.actualizarFacturacionPoliza(paramFact));
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
