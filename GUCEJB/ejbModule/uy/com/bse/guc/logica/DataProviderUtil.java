package uy.com.bse.guc.logica;

import uy.com.bse.guc.persistencia.GUCDataProvider;
import uy.com.bse.guc.persistencia.impl.GUCPersist;

public final class DataProviderUtil {

	public static GUCDataProvider getDataProvider(){
		//FIXME OIGRES CAMBIAR A GUCLDAP SI ES NECESARIO LDAP UNA VEZ SE IMPLEMENTE.
		return new GUCPersist();
	}
}
