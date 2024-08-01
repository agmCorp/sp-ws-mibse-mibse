package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.polizas.operaciones.ValidacionGenerica;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosRenovacion extends ResultGenerico{
	private DatosCotizacion datosCotizacion;
	private DatosContratante datosContratante;
	private ValidacionGenerica confirmacion;
	private String tieneNotas;
	
	public DatosCotizacion getDatosCotizacion() {
		return datosCotizacion;
	}
	public void setDatosCotizacion(DatosCotizacion datosCotizacion) {
		this.datosCotizacion = datosCotizacion;
	}
	public DatosContratante getDatosContratante() {
		return datosContratante;
	}
	public void setDatosContratante(DatosContratante datosContratante) {
		this.datosContratante = datosContratante;
	}
	public ValidacionGenerica getConfirmacion() {
		return confirmacion;
	}
	public void setConfirmacion(ValidacionGenerica confirmacion) {
		this.confirmacion = confirmacion;
	}
	public String getTieneNotas() {
		return tieneNotas;
	}
	public void setTieneNotas(String tieneNotas) {
		this.tieneNotas = tieneNotas;
	}
	

}
