package uy.com.bse.mibse.sp.ws.mibse.utilitario.exception;

import java.util.HashMap;
import java.util.Map;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ServiciosError;

public final class ErrorResolver {
	private final static Map<String, ServiciosError> errores = new HashMap<String, ServiciosError>();

	static {
		errores.put(Values.CLAVEINCORRECTA, new ServiciosError(2, "Ocurri\u00f3 un error inesperado. Por favor vuelva a ingresar al sistema."));
		errores.put(Values.VALNULL, new ServiciosError(3, "Existen datos requeridos que no se han especificado."));
		errores.put(Values.VALNUMERICO, new ServiciosError(4, "Existen datos num\u00e9ricos que no se han especificado como tal."));
		errores.put(Values.VALMESANNO, new ServiciosError(5, "Existen datos con formato mes/a\u00f1o que no se han especificado como tal."));
		errores.put(Values.CLWEBSERVEXCP, new ServiciosError(100, "Ocurri\u00f3 un error inesperado. Por favor comun\u00edquese con BSE. (100)"));
		errores.put(Values.NEGOCIOEXCP, new ServiciosError(12, "Ocurri\u00f3 un error inesperado. Por favor comun\u00edquese con BSE. (12)"));
		errores.put(Values.PERSEXCEPTCOD, new ServiciosError(21, "Ocurri\u00f3 un error de datos. Por favor comun\u00edquese con BSE. (21)"));
		errores.put(Values.PARSEXCEPTCLAVE, new ServiciosError(22, "Ocurri\u00f3 un error de datos. Por favor comun\u00edquese con BSE. (22)"));
		errores.put(Values.PARSEXCEPESTRUCTURA, new ServiciosError(40, "Ocurri\u00f3 un error de datos. Por favor comun\u00edquese con BSE. (40)"));
		errores.put(Values.PARSNUMEXCEPTCLAVE, new ServiciosError(51, "Ocurri\u00f3 un error de datos. Por favor comun\u00edquese con BSE. (51)"));
		errores.put(Values.SQLEXCEPT, new ServiciosError(60, "Ocurri\u00f3 un error de datos. Por favor comun\u00edquese con BSE. (60)"));
		errores.put(Values.NOTUPDATED, new ServiciosError(300, "Ocurri\u00f3 un error de datos. Por favor comun\u00edquese con BSE. (300)"));
		errores.put(Values.EXCEPTION, new ServiciosError(1, "Ocurri\u00f3 un error inesperado. Por favor reintente m\u00e1s tarde."));
		errores.put(Values.TIMEOUTEXCEPTION, new ServiciosError(700, "Ocurri\u00f3 un error inesperado. Por favor reintente m\u00e1s tarde. (700)"));
		errores.put(Values.CONFIGURATION_EXCEPTION, new ServiciosError(750, "Ocurri\u00f3 un error de configuraci\u00f3n. Por favor comun\u00edquese con BSE. (750)"));
	}

	public static ServiciosError getError(String clave) {
		ServiciosError error = null;
		if (clave != null) {
			if (errores.containsKey(clave)) {
				error = errores.get(clave);
			} else {
				error = errores.get(Values.EXCEPTION);
			}
		}
		return error;
	}
}
