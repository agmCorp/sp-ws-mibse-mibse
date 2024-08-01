package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCotizacionPendiente extends ResultGenerico{
	private static final long serialVersionUID = 1L;
	private ValidacionGenerica confirmacion;

	public ValidacionGenerica getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(ValidacionGenerica confirmacion) {
		this.confirmacion = confirmacion;
	}
	
}
