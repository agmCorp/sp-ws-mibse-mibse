package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamLogin extends ParamGenerico {

	private static final long serialVersionUID = 1L;
	private String contrasena;
	private String idSession;
	private String applicationID;

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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
