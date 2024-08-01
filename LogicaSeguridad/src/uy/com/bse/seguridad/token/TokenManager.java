package uy.com.bse.seguridad.token;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TokenManager {
	
	private static Map<String, TokenRow> sessionMap = new HashMap<String, TokenRow>();
	private static Map<String, TokenRow> tokenMap = new HashMap<String, TokenRow>();

	public static TokenRow removeRow(String token) {
		TokenRow row = getRow(token);
		if (row != null) {
			sessionMap.remove(row.getSessionID());
			tokenMap.remove(row.getToken());
		}
		return row;
	}

	public static TokenRow getRow(String token) {
		return tokenMap.get(token);
	}

	public static TokenRow createTokenRow(String sessionID, String userName) {
		String token = null; //usar algoritmo!!!
		//token="claveExterna";
		token = new String(System.currentTimeMillis()+UUID.randomUUID().toString());
		TokenRow row = new TokenRow(sessionID, userName, token);
		sessionMap.put(sessionID, row);
		tokenMap.put(token, row);
		return row;
	}
	
	public static TokenRow createTokenRow(String sessionID, String userName,String userCorto) {
		String token = null; //usar algoritmo!!!
		//token="claveExterna";
		token = new String(System.currentTimeMillis()+UUID.randomUUID().toString());
		TokenRow row = new TokenRowUserLargo(sessionID, userName, token,userCorto);
		sessionMap.put(sessionID, row);
		tokenMap.put(token, row);
		return row;
	}

}
