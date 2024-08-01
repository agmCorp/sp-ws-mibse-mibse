package uy.com.bse.seguridad.token;

public final class MemoryTokenResolver implements TokenResolver {

	@Override
	public TokenRow removeRow(String token) {
		return TokenManager.removeRow(token);
	}

	@Override
	public TokenRow getRow(String token) {
		return TokenManager.getRow(token);
	}

	@Override
	public TokenRow createTokenRow(String sessionID, String userName) {
		return TokenManager.createTokenRow(sessionID, userName);
	}

	@Override
	public TokenRow createTokenRow(String sessionID, String userName, String userCorto) {
		return TokenManager.createTokenRow(sessionID, userName,userCorto);
	}

}
