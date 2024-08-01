package uy.com.bse.seguridad.token;

public class TokenRowUserLargo extends TokenRow {

	private String user;
	public TokenRowUserLargo(String sessionID2, String userLargo, String token2, String userCorto) {
		super(sessionID2, userLargo, token2);
		this.user=userCorto;
		
	}
	@Override
	public String getUserName() {
		return this.user;
	}
	

}
