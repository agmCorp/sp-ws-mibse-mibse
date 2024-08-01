package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularRec extends ResultGenerico{
	private DetalleRecuotificacion detalleRecuotificacion;

	public DetalleRecuotificacion getDetalleRecuotificacion() {
		return detalleRecuotificacion;
	}

	public void setDetalleRecuotificacion(
			DetalleRecuotificacion detalleRecuotificacion) {
		this.detalleRecuotificacion = detalleRecuotificacion;
	}

}
