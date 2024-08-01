package uy.com.bse.recuotificacion;

import uy.com.bse.polizas.operaciones.ValidacionGenerica;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarPolizaRec extends ResultGenerico{
	private Recuotificacion datosRecuotificacion;
	private Integer numCotizacionPendiente;
	private ValidacionGenerica confirmacion;
	
	public Recuotificacion getDatosRecuotificacion() {
		return datosRecuotificacion;
	}
	public void setDatosRecuotificacion(Recuotificacion datosRecuotificacion) {
		this.datosRecuotificacion = datosRecuotificacion;
	}
	public Integer getNumCotizacionPendiente() {
		return numCotizacionPendiente;
	}
	public void setNumCotizacionPendiente(Integer numCotizacionPendiente) {
		this.numCotizacionPendiente = numCotizacionPendiente;
	}
	public ValidacionGenerica getConfirmacion() {
		return confirmacion;
	}
	public void setConfirmacion(ValidacionGenerica confirmacion) {
		this.confirmacion = confirmacion;
	}
	
	
}
