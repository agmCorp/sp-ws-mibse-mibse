package uy.com.bse.seguridad;

import java.rmi.RemoteException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.seguridad.ldap.AutenticadorLDAP;
import uy.com.bse.seguridad.ldap.LDAPException;
import uy.com.bse.seguridad.persistencia.ServiciosSeguridadPersistencia;
import uy.com.bse.seguridad.properties.AutenticadoresConfiables;
import uy.com.bse.seguridad.token.MemoryTokenResolver;
import uy.com.bse.seguridad.token.TokenResolver;
import uy.com.bse.seguridad.token.TokenRow;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.LogicaSolver;
import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ResultLogin;

public final class LoginSolver implements LogicaSolver {

	private static final Logger LOG = LogManager.getLogger(LoginSolver.class);

	private TokenResolver resolver = new MemoryTokenResolver();

	@Override
	public ResultGenerico solve(ParamGenerico param) {
		ResultLogin result = null;
		ParamLogin myParam = (ParamLogin) param;
		if (myParam != null) {
			result = new ResultLogin();
			String appUser = myParam.getApplicationID();
			String appPassword = myParam.getClave();
			String sessionID = myParam.getIdSession();
			String userName = myParam.getUsuario();
			String token;
			try {
				if (userName.contains("=")) {
					token = loginFullName(appUser, appPassword, sessionID, userName);
				} else {
					token = login(appUser, appPassword, sessionID, userName);
				}

				if (token != null) {
					result.setToken(token);
				} else {
					result.setHayError(Boolean.TRUE);
					ServiciosError error = new ServiciosError();
					error.setCodigo(1);
					error.setDescripcion("No se pudo obtener token de seguridad en login");
					result.setError(error);
					LOG.info("No se pudo obtener token de seguridad en login para " + userName);
				}
			} catch (RemoteException e) {
				LOG.error(e);
			}

		}

		return result;
	}

	private String login(String appUser, String appPassword, String sessionID, String userName) throws RemoteException {
		String token = null;
		if ((appUser != null) && (userName != null)) {
			// Autentico contra el ldap, las credenciales provistas
			if (AutenticadorLDAP.validate(appUser, appPassword)) {
				// Autentique al portal (o al usuario que venga como autenticador origen)
				if ((appUser.equals(userName)) || (esAutenticador(appUser))) {
						boolean existeUsuarioRector = existeUsuarioRector(userName);
					
					if (existeUsuarioRector) {
						// doy de alta la sessionID, y la asocio al usuario
						TokenRow row = resolver.createTokenRow(sessionID, userName); // TokenManager.createTokenRow(sessionID,userName);
						token = row.getToken();
						LOG.info("login ok para usuario: " + userName);
						auditarLogin(appUser, userName);
					}
				}
			}

		}

		return token;
	}
	
	private String loginFullName(String appUser, String appPassword, String sessionID, String userName) throws RemoteException {
		String token = null;
		if ((appUser != null) && (userName != null)) {
			// Autentico contra el ldap, las credenciales provistas
			if (AutenticadorLDAP.validate(appUser, appPassword)) {
				// Autentique al portal (o al usuario que venga como autenticador origen)
				if ((appUser.equals(userName)) || (esAutenticador(appUser))) {
					String user = userName;
					boolean existeUsuarioRector = false;
						try {
							user = AutenticadorLDAP.getUserName(userName,appUser, appPassword);
							if (!user.contains("@")) {
								existeUsuarioRector = existeUsuarioRector(user);
								if (existeUsuarioRector) {
									
									TokenRow row = resolver.createTokenRow(sessionID, userName,user);
									token = row.getToken();
									LOG.info("login ok para usuario: " + userName);
									auditarLogin(appUser, user);
								}
							}else {
								existeUsuarioRector = existeUsuarioRector(user.split("@")[0]);
								if (existeUsuarioRector) {
								
									TokenRow row = resolver.createTokenRow(sessionID, userName,user.split("@")[0]);
									token = row.getToken();
									LOG.info("login ok para usuario: " + userName);
									auditarLogin(appUser, user);
								}
								}
						} catch (LDAPException e) {
							LOG.error(e);
						}
					
					
				}
			}

		}

		return token;
	}

	public boolean existeUsuarioRector(String userName) {
		Boolean existeUsuario = false;
		ServiciosSeguridadPersistencia p = new ServiciosSeguridadPersistencia();
		existeUsuario = p.existeUsuarioRector(userName);
		return existeUsuario;
	}

	private static boolean esAutenticador(String appUser) {
		String[] auths = AutenticadoresConfiables.listaActual();
		boolean salida = false;
		for (int i = 0; i < auths.length && !salida; i++) {
			String string = auths[i];
			salida = string.equals(appUser);
		}
		return salida;
	}

	private void auditarLogin(String appUser, String userName) {
		ServiciosSeguridadPersistencia p = new ServiciosSeguridadPersistencia();
		p.auditarLogin(appUser, userName);
	}

}
