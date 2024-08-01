package uy.com.bse.seguridad.ldap;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import uy.com.bse.seguridad.properties.SeguridadServiciosProperty;

public final class AutenticadorCache {

	private static Map<String, Long> autenticaciones = new HashMap<String, Long>();

	public static boolean validar(String user, String password) {
		boolean salida = false;
		Long originalTime = autenticaciones.get(buildKey(user, password));
		if (originalTime != null) {
			long TIMEOUT = SeguridadServiciosProperty.getCACHEAutenticacion_Timeout();
			boolean expired = System.currentTimeMillis() > (originalTime + TIMEOUT);
			salida = !expired;
		}
		return salida;
	}
	public static void agregar(String user, String password) {
		autenticaciones.put(buildKey(user, password), System.currentTimeMillis());
	}
	
	private static String buildKey(String user, String password) {
		String string = user+password;
        return DatatypeConverter.printBase64Binary(string.getBytes());
    }
	
}
