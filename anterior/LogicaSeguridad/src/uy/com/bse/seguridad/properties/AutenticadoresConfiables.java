package uy.com.bse.seguridad.properties;

import uy.com.bse.utilitario.properties.CachedPropertiesReader;

public final class AutenticadoresConfiables {

	private final static String AUTENTICADORES_CONFIABLES = "/autenticadoresConfiables.properties";
	public static String[] listaActual() {
		return CachedPropertiesReader.getPropertyKeys(AUTENTICADORES_CONFIABLES);
	}

}
