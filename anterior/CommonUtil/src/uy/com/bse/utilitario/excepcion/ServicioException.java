package uy.com.bse.utilitario.excepcion;

public final class ServicioException extends RuntimeException {

	public ServicioException(String genericMessage) {
		super(genericMessage);
	}

	private static final long serialVersionUID = 1L;

}
