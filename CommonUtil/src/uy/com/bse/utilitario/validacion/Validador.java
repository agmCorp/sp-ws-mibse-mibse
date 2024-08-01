package uy.com.bse.utilitario.validacion;

import uy.com.bse.utilitario.dato.ServiciosError;

public interface Validador<T> {

	public ServiciosError validate (T object);
	
	
}
