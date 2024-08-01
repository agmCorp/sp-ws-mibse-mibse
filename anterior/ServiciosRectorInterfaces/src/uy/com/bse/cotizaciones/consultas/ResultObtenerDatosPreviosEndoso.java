package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosPreviosEndoso extends ResultGenerico{
	private DatosCotizacion cotizacion;
	private DatosContratante contratante;
	
	public DatosCotizacion getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(DatosCotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	public DatosContratante getContratante() {
		return contratante;
	}
	public void setContratante(DatosContratante contratante) {
		this.contratante = contratante;
	}
	
}
