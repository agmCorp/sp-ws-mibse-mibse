package uy.com.bse.polizas.consultas;

import uy.com.bse.cotizaciones.consultas.Ubicacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerUbicacionBienPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza;
	private Ubicacion ubicacion;
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

}
