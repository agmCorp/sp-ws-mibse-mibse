package uy.com.bse.utilitario.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uy.com.bse.utilitario.dato.ServiciosError;

public class ValidadorCelular implements Validador<String> {

	private static final Pattern VALID_CELULAR_REGEX = 
		    Pattern.compile("^09+\\d{7}$");
	@Override
	public ServiciosError validate(String celular) {
		ServiciosError error =null;
		Matcher matcher = VALID_CELULAR_REGEX .matcher(celular);
	        if(!matcher.find()){
	         error = new ServiciosError(24,"El n\u00fa	mero de 'Celular' debe comenzar con '09' y tener un largo de 9 caracteres.");
	        }
	        return error;
	}

}
