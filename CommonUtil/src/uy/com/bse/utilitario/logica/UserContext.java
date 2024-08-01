package uy.com.bse.utilitario.logica;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserContext implements Serializable {

	private static final long serialVersionUID = 1L;

	public UserContext(String key) {
		idContext = key;
	}

	private String idContext; //Identificador = SessionID
	private String user; // Usuario final = Usuario con que se hace login al portal
	private String applicationID, applicationPwd; // usuario y password que identifican al Portal (unico para todo lo que corre en Portal)
	private String token; //Token de seguridad, devuelto por el servicio de "login"
	private long createdTime = System.currentTimeMillis();

	/* 
	 * ojo con poner cosas 'no serializables' porque sabemos que no viajan.
	 * Tampoco se replican si tenemos replica de sesiones.
	 */
	private Map<String, Object> objetos = new HashMap<String, Object>();
	
	public String getIdContext() {
		return idContext;
	}

	public void setIdContext(String idContext) {
		this.idContext = idContext;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	@Override
	public String toString() {
		return new String("UserContext id["+idContext+"] user["+user+"], app["+applicationID+"], token["+token+"], created["+createdTime+"].");
	}

	public String getApplicationPwd() {
		return applicationPwd;
	}
	
	public void setApplicationPwd(String applicationPwd) {
		this.applicationPwd = applicationPwd;
	}

	public void agregarObjeto(String key, Object value) {
		objetos.put(key, value);
	}
	public Object obtenerbjeto(String key) {
		return objetos.get(key);
	}

	public boolean isLogged(String userName) {
		if (userName!=null) {
			return ((token!=null) && (userName.equals(user)));
		} else {
			return false;
		}
	}

	
}
