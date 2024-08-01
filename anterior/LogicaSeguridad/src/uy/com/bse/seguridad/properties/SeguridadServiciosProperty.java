package uy.com.bse.seguridad.properties;

import uy.com.bse.utilitario.properties.CachedPropertiesReader;


public final class SeguridadServiciosProperty {

	private static final String SEGURIDAD_SERVICIOS = "/seguridadServicios.properties";

	public static String getLDAP_URL() {
		return CachedPropertiesReader.getPropertyValue(SEGURIDAD_SERVICIOS, "url_LDAP");

		//return "ldaps://10.1.1.21";
	}

	public static String getLDAP_Timeout() {
		//return "1000";
		return CachedPropertiesReader.getPropertyValue(SEGURIDAD_SERVICIOS, "timeoutLDAP");
	}

	public static long getCACHEAutenticacion_Timeout() {
		long salida = 0;
		String value = CachedPropertiesReader.getPropertyValue(SEGURIDAD_SERVICIOS, "timeoutCacheAutenticacion");
		if (value != null) {
			salida = Long.parseLong(value);
		}
		//return 60000;
		return salida;
	}

}
