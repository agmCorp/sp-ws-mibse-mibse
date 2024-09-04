package uy.com.bse.mibse.sp.ws.mibse.utilitario.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;

@Component
public class Herramientas {
	
	private static Logger log = LogManager.getLogger(Herramientas.class);

	/**
	 * Toma la fecha del sistema
	 * @param formato
	 * @return Un string con la fecha actual en el formato ingresado.
	 */
	public String getFechaActual(String formato) {
		String date = null;
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		date = sdf.format(cal.getTime());

		return date;
	}

	/**
	 * Toma la fecha del sistema
	 * @param separador
	 * @param horas
	 * @return Un string con la fecha actual, con el separador ingresado 
	 * y con la hora si el parametro horas está en true.
	 */
	public String getFechaActual(String separador, Boolean horas) {
		String date = null;

		Calendar cal = Calendar.getInstance();
		String format = "dd" + separador + "MM" + separador + "yyyy";

		if (horas) {
			format += " HH:mm";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.format(cal.getTime());


		return date;
	}

	/**
	 * Convierte un Clob en String
	 * @param unClob
	 * @return Un string con el contenido del Clob ingresado, null si el Clob ingresado era ya un null 
	 * o si se generó un error en la conversión.
	 */
	public String convertirClob(Clob unClob) {
		Logueo logueo = new Logueo();
		String resultado = null;

		logueo.setEncabezado("Error al convertir Clob a String");
		logueo.setClase(Herramientas.class);
		logueo.setMetodo("convertirClob");

		if (unClob != null) {
			StringBuffer str = new StringBuffer();
			String strng;

			BufferedReader bufferRead;
			try {
				bufferRead = new BufferedReader(unClob.getCharacterStream());
				while ((strng = bufferRead.readLine()) != null)
					str.append(strng + "\n");
				resultado = str.toString();
			} catch (SQLException e) {
				resultado = null;
				logueo.setException("SQLException");
				logueo.setError(e.getMessage());
				log.error(logueo.getMensaje());
			} catch (IOException e) {
				resultado = null;
				logueo.setException("IOException");
				logueo.setError(e.getMessage());
				log.error(logueo.getMensaje());
			} catch (Exception ex) {
				resultado = null;
				logueo.setException("Exception");
				logueo.setError(ex.getMessage());
				log.error(logueo.getMensaje());
			}
		}

		return resultado;
	}
	
	public String establecerMascara(Double numero){
		
		//1- Si la parte decimal no tiene la cantidad de numeros, relleno con 0
		//2- Si tiene mas, lo redondea solo.
		//3- El resto de la mascara es automatica en este metodo
		//4- Quizas otro metodo para retornar otras mascaras
		
		/*
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setDecimalSeparator('|');
		formatSymbols.setGroupingSeparator(' ');

		String strange = "#,##0.###";
		DecimalFormat df = new DecimalFormat(strange, formatSymbols);
		df.setGroupingSize(4);

		String out = df.format(new BigDecimal(300000).doubleValue());*/
		
		//DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		//formatSymbols.setDecimalSeparator('|');
		//formatSymbols.setGroupingSeparator('.');

		//String strange = "#,##.####";
		String strange = "#,#.###";
		//DecimalFormat df = new DecimalFormat(strange, formatSymbols);
		DecimalFormat df = new DecimalFormat(strange);
		//double value = 12.3457652133;
		//double value = 12.34;
		//String out = new DecimalFormat("##.####").format(value);
		df.setGroupingSize(3);

		String out = df.format(new BigDecimal(3000000.15).doubleValue());

		
		
		return out;
	}
	
	/**
	 * Comprueba que el string recibido sea numérico y pueda ser casteado utilizando expresiones regulares
	 * @param textoComparar
	 * @return
	 */
	public boolean isNumeric(String textoComparar) {  
	    return textoComparar.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	/**
	 * Comprueba que el valor recibido de un properties es numerico, comprueba que no sea null ni vacio
	 * @param valorDefecto
	 * @param valorComprobar
	 * @return El valor por defecto que es el primer parámetro recibido
	 */
	public Integer checkValorPropertiesNumerico(Integer valorDefecto, String valorComprobar){
		Integer codigo = new Integer(valorDefecto);
		
		if(valorComprobar != null){	
			if(this.isNumeric(valorComprobar)){
				codigo = new Integer(valorComprobar);
			}
		}
		
		return codigo;
	}
	
	
	/**
	 * Comprueba que el valor recibido de un properties no sea null ni vacío
	 * @param valorDefecto
	 * @param valorComprobar
	 * @return El valor por defecto que es el primer parámetro recibido
	 */
	public String checkValorPropertiesString(String valorDefecto, String valorComprobar){
		String descripcion = valorDefecto;
		
		if(valorComprobar != null && !valorComprobar.equals("")){
			descripcion = valorComprobar;
		}
		
		return descripcion;
	}
}
