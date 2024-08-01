package uy.com.bse.utilitario.util;

import java.util.Date;

public class ValuesUtils {

	public static String toString(String value){
		if (value!=null && !value.isEmpty() && !"".equals(value)) {
			return value;
		}
		return null;
	}
	
	public static String toString(Date value){
		return value == null ? null : FechaConverter.convertirFecha(value);
	}
	
	public static String toString(Integer value){
		return value == null ? null : String.valueOf(value);
	}
	
	public static String toString(Double value){
		return value == null ? null : String.valueOf(value);
	}
	
	public static Integer toInteger(String value){
		if (value!=null&& !value.isEmpty()) {
			return Integer.parseInt(value);
		}
		return null;
	}
	
	public static Double toDouble(String value){
		if (value!=null&& !value.isEmpty()) {
			return Double.parseDouble(value);
		}
		return null;
	}
	
	public static String emptyNull(String value){
		return (value==null)?"":value;
	}

	public static String toString(Long value) {
		return value == null ? null : String.valueOf(value);
	}
	
	public static String buildString(String[]values){
		StringBuffer sb =new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
		}
		return sb.toString();
	}
	
	public static Date toDate(String fecha){
		return FechaConverter.convertirFecha(fecha);
	}
	
	
	public static String toStringTrimmed(String value){
		if (value!=null && !value.isEmpty() && !"".equals(value)) {
		return value.trim();
		}
		return null;
		}

	public static Integer toInteger(char entrada) {
		String value = entrada+"";			
		if (value!=null&& !value.isEmpty()) {
			return Integer.parseInt(value);
		}
		return null;
	}
}
