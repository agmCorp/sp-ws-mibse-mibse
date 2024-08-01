package uy.com.bse.utilitario.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uy.com.bse.utilitario.dato.ServiciosError;

public class ValidadorMail implements Validador<String> {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Override
	public ServiciosError validate(String mail) {
		ServiciosError error =null;
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
	        if(!matcher.find()){
	         error = new ServiciosError(25,"El correo electr\u00f3nico es inv\u00e1lido");
	        }
	        return error;
	}

}
