package uy.com.bse.utilitario.validacion;

import uy.com.bse.utilitario.dato.ServiciosError;

public class ValidadorMesAnio implements Validador<String> {

	@Override
	public ServiciosError validate(String valor) {
		ServiciosError error = null;
		if (valor == null || valor.length() != 7) {
			error = new ServiciosError(26,"El formato mes/a\u00f1o es inv\u00e1lido. Ejemplo: 02/1998");
		} else {
			String[] partes = valor.split("/");
			if (partes.length == 2) {
				if (!partes[0].matches("^(0?[1-9]|1[012])$")) {
					error = new ServiciosError(26,"El formato mes/a\u00f1o es inv\u00e1lido. Ejemplo: 02/1998");
				}
				if (!partes[1].matches("^[1-9]\\d{3,}$")) {
					error = new ServiciosError(26,"El formato mes/a\u00f1o es inv\u00e1lido. Ejemplo: 02/1998");
				}
			} else {
				error = new ServiciosError(26,"El formato mes/a\u00f1o es inv\u00e1lido. Ejemplo: 02/1998");
			}
		}
		return error;
	}

}
