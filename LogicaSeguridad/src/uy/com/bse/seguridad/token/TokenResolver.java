package uy.com.bse.seguridad.token;


public interface TokenResolver {

	public  TokenRow removeRow(String token) ;

	public  TokenRow getRow(String token);
		
	public  TokenRow createTokenRow(String sessionID, String userName) ;
	
	public  TokenRow createTokenRow(String sessionID, String userName,String userCorto) ;
}
