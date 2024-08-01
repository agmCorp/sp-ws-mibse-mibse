package uy.com.bse.seguridad.token;

import uy.com.bse.utilitario.dato.ResultXmlPL;

public class DataBaseTokenResolver implements TokenResolver {

	@Override
	public TokenRow removeRow(String token) {
		//FIXME OIGRES NOT IMPLEMENTED
		//return parseToken(new ServiciosSeguridadPersistencia().removeRow(token));
		return null;
	}
	
	@Override
	public TokenRow getRow(String token) {
		//FIXME OIGRES NOT IMPLEMENTED
		//return parseToken(new ServiciosSeguridadPersistencia().getRow(token));
		return null;
	}

	@Override
	public TokenRow createTokenRow(String sessionID, String userName) {
		//FIXME OIGRES NOT IMPLEMENTED
		//String token = new String(System.currentTimeMillis()+UUID.randomUUID().toString());
		// return parseToken(new ServiciosSeguridadPersistencia().createTokenRow(token, sessionID, userName));
		return null;
	}
	
	private TokenRow parseToken(ResultXmlPL xml) {
		//FIXME OIGRES ParsearEsto
		//FIXME OIGRES NOT IMPLEMENTED
		TokenRow row = new TokenRow("password", "admin", "123");
		return null;
	}

	@Override
	public TokenRow createTokenRow(String sessionID, String userName, String userCorto) {
		//FIXME OIGRES ParsearEsto
				//FIXME OIGRES NOT IMPLEMENTED
				TokenRow row = new TokenRow("password", "admin", "123");
				return null;
	}


}
