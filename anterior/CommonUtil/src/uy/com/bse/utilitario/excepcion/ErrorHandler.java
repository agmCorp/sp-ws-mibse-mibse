package uy.com.bse.utilitario.excepcion;

import uy.com.bse.utilitario.logica.LogicaSolver;

public final class ErrorHandler {

	static String genericMessage = "Error de servicio, consulte a Soporte BSE";
	public static void handle(Class<LogicaSolver> classOrigen) {
		throw new ServicioException(genericMessage);
	}

	public static void handle(Class<LogicaSolver> classOrigen, String methodName) {
		throw new ServicioException(genericMessage);
	}

	public static void handle(Class<LogicaSolver> classOrigen, String methodName, String message) {
		throw new ServicioException(genericMessage);
	}

}
