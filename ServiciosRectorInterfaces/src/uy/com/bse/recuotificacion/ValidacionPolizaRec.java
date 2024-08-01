package uy.com.bse.recuotificacion;

import uy.com.bse.polizas.operaciones.ValidacionGenerica;

public class ValidacionPolizaRec extends ValidacionGenerica{
	private Integer numCotizacionPendiente;

	public Integer getNumCotizacionPendiente() {
		return numCotizacionPendiente;
	}

	public void setNumCotizacionPendiente(Integer numCotizacionPendiente) {
		this.numCotizacionPendiente = numCotizacionPendiente;
	}
}
