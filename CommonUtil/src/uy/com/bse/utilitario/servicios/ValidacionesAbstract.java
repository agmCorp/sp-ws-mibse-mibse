package uy.com.bse.utilitario.servicios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;

public abstract class ValidacionesAbstract {
	
	private static final Logger LOG = LogManager.getLogger(ValidacionesAbstract.class);
	
	protected ServiciosError getErrorsByClave(String claveDeError) {
		ServiciosError error= ErrorResolver.getError(claveDeError);
		if (error != null) {
			final StringBuffer resultado = new StringBuffer();
			resultado.append("\n********************************");
			resultado.append("\n- ");
			resultado.append("ERROR DE VALIDACION: ");
			resultado.append(error.getDescripcion());
			resultado.append(" -");
			resultado.append("\n********************************");
			LOG.info(resultado.toString());
		}
		return error;
	}

	protected boolean isNumeric(String numero) {
		return numero.matches("[-+]?\\d*\\.?\\d+");
	}

	public ValidacionesAbstract() {
		super();
		
	}
	
	

}
