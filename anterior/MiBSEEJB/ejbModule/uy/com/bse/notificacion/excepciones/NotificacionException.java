package uy.com.bse.notificacion.excepciones;

public class NotificacionException extends Exception {
	private static final long serialVersionUID = 8294689833518238684L;
	
	public NotificacionException() {
		super("Error al notificar al Cliente");
	}

	public NotificacionException(String message) {
		super(message);
	}

	public NotificacionException(Throwable t) {
		super(t);
	}

	public NotificacionException(String message, Throwable t) {
		super(message, t);
	}
}
