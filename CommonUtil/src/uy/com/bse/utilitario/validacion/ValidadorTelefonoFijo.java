package uy.com.bse.utilitario.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uy.com.bse.utilitario.dato.ServiciosError;

public class ValidadorTelefonoFijo implements Validador<String> {

	private static final Pattern VALID_TELEFONO_REGEX = Pattern.compile("^(2|4)+\\d{7}$");

	@Override
	public ServiciosError validate(String celular) {
		ServiciosError error = null;
		Matcher matcher = VALID_TELEFONO_REGEX.matcher(celular);
		if (!matcher.find()) {
			error = new ServiciosError(27,"El n\u00famero de 'Tel\u00e9fono fijo' debe comenzar con 2 o 4 y tener un largo de 8 caracteres.");
		}
		return error;
	}

}