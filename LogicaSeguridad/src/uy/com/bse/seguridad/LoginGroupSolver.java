package uy.com.bse.seguridad;

import java.rmi.RemoteException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.seguridad.ldap.AutenticadorLDAP;
import uy.com.bse.seguridad.persistencia.ServiciosSeguridadPersistencia;
import uy.com.bse.seguridad.properties.AutenticadoresConfiables;
import uy.com.bse.seguridad.token.MemoryTokenResolver;
import uy.com.bse.seguridad.token.TokenResolver;
import uy.com.bse.seguridad.token.TokenRow;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.LogicaSolver;
import uy.com.bse.utilitario.seguridad.ParamLoginGroup;
import uy.com.bse.utilitario.seguridad.ResultLoginGroup;


public final class LoginGroupSolver implements LogicaSolver {

	private static final Logger LOG = LogManager.getLogger(LoginGroupSolver.class);
	
	
	
	private TokenResolver resolver = new MemoryTokenResolver();
	
	@Override
	public ResultGenerico solve(ParamGenerico param) {
		ResultLoginGroup result = null;
		ParamLoginGroup myParam = (ParamLoginGroup)param;
		if (myParam != null) {
			result = new ResultLoginGroup();
			String appUser = myParam.getApplicationID();
			String appPassword = myParam.getClave();
			String sessionID = myParam.getIdSession();
			String userName = myParam.getUsuario();
			String token;
			try {
				token = login(appUser, appPassword, sessionID, userName);
			
			if (token != null) {
				result.setToken(token);
			} else {
				result.setHayError(Boolean.TRUE);
				ServiciosError error = new ServiciosError();
				error.setCodigo(1); error.setDescripcion("No se pudo obtener token de seguridad en login");
				result.setError(error);
				LOG.info("No se pudo obtener token de seguridad en login para "+ userName);
			}
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
		
		}
		
		return result;
	}

	private String login(String appUser, String appPassword, String sessionID, String userName) throws RemoteException {
		String token = null;
		if ((appUser != null) && (userName != null)) {
			//Autentico contra el ldap, las credenciales provistas
			if (AutenticadorLDAP.validateGroup(appUser, appPassword)) {
				// Autentique al portal (o al usuario que venga como autenticador origen)
				if ((appUser.equals(userName)) || (esAutenticador(appUser))) {
					boolean existeUsuarioRector = existeUsuarioRector(userName);
					
					
					if (existeUsuarioRector) {
					//doy de alta la sessionID, y la asocio al usuario
						TokenRow row = resolver.createTokenRow(sessionID, userName); //TokenManager.createTokenRow(sessionID, userName);
						token = row.getToken();
						LOG.info("login ok para usuario: " + userName);
						auditarLogin(appUser, userName);
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
	
	private void auditarLogin(String appUser,String userName){
		ServiciosSeguridadPersistencia p = new ServiciosSeguridadPersistencia();
		p.auditarLogin(appUser,userName);
	}
	
	
	
}
