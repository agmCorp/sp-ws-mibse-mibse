package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarAnulacionPoliza extends ResultGenerico{
	private CotizacionAnulacion cotizacionAnulacion;
	
	public CotizacionAnulacion getCotizacionAnulacion() {
		return cotizacionAnulacion;
	}
	public void setCotizacionAnulacion(CotizacionAnulacion cotizacionAnulacion) {
		this.cotizacionAnulacion = cotizacionAnulacion;
	}

}
