package uy.com.bse.seguridad.token;

public class TokenRow {
	
	String token, sessionID, userName;

	public TokenRow(String sessionID2, String userName2, String token2) {
		token = token2;
		sessionID=sessionID2;
		userName=userName2;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean validateUserName(String userName) {
		return this.userName.equals(userName);
	}

}
