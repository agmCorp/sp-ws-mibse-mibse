package uy.com.bse.mibse.sp.ws.mibse.ws;

import org.springframework.beans.factory.annotation.Autowired;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ServiciosError;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.servicios.ValidacionesAbstract;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamValidar;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ResultValidar;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;


public class ValidacionesMiBse extends ValidacionesAbstract {

	@Autowired
	ParamValidar paramValidar;
	@Autowired
	ResultValidar resultValidar;

	public ServiciosError validarObtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		return getErrorsByClave(errorValidarLogin(param));
	}


	private String errorValidarLogin(ParamGenerico param) {
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			return Values.CLAVEINCORRECTA;
		}
		return null;
	}


	protected boolean validarLogin(String usuario, String clave) {
		// TODO aguirrea implementar esto, parece que termina llamando a un SP después de marearla... 
		// TIP: simplificar
		return true;
	}

}
