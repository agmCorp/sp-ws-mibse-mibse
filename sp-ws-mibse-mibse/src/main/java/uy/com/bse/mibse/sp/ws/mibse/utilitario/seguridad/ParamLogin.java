package uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import org.springframework.stereotype.Component;

@Component
public class ParamLogin extends ParamGenerico {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Sesion del usuario final, el que se loguea al portal
	String idSession;
	
	/* Usuario que identifica al POrtal (o la aplicacion cliente que fuere).
	 * Para la clave de aplicacion usaremos el atributo 'clave' del padre, dado que no hay token generado aun.
	 */
	String applicationID;

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}


}
