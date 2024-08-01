package uy.com.bse.utilitario.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FechaConverter {

	private static String pattern = "dd/MM/yyyy";
	private static String patternGuion = "dd-MM-yyyy";
	private static final Logger LOG = LogManager.getLogger(FechaConverter.class);

	public static Date convertirFecha(String fecha) {
		Date fechaReturn = null;
		if (fecha != null) {
			int tieneGuion = fecha.indexOf('-');
			SimpleDateFormat format = null;
			if (tieneGuion >= 0) {
				format = new SimpleDateFormat(patternGuion);
			} else {
				format = new SimpleDateFormat(pattern);
			}

			try {
				fechaReturn = format.parse(fecha);
			} catch (ParseException e) {
				LOG.error("error al convertir la fecha:" + fecha, e);
			}
		}
		return fechaReturn;
	}

	public static String convertirFecha(Date fecha) {
		String resultado;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		resultado = format.format(fecha);

		return resultado;

	}

}
