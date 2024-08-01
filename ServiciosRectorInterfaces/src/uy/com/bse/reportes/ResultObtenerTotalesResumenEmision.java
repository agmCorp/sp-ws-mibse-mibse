package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerTotalesResumenEmision extends ResultGenerico {
	private static final long serialVersionUID = 4287176032373775965L;
	
	private Integer cantidadAutomaticas;
	private Integer cantidadManuales;
	private Integer cantidadRenovadas;
	private Integer cantidadModificadas;
	private Integer cantidadAnuladas;
	private Integer cantidadNoRenuevan;
	private Integer cantidadNuevas;
	private Integer cantidadTotalPolizas;
	
	private Integer cantidadFueraPauta;
	private Integer cantidadEnPauta;
	private Integer totalCotizacionesRenovacion;
	private Integer cantidadCotizacionRenovacionModificadas;
	
	private Integer cantidadPolizas;
	private Integer cantidadCotizaciones;
	private Integer restaTotalesPolizasCotizacion;

	public Integer getRestaTotalesPolizasCotizacion() {
		return restaTotalesPolizasCotizacion;
	}

	public void setRestaTotalesPolizasCotizacion(Integer restaTotalesPolizasCotizacion) {
		this.restaTotalesPolizasCotizacion = restaTotalesPolizasCotizacion;
	}

	public Integer getCantidadAutomaticas() {
		return cantidadAutomaticas;
	}

	public void setCantidadAutomaticas(Integer cantidadAutomaticas) {
		this.cantidadAutomaticas = cantidadAutomaticas;
	}

	public Integer getCantidadManuales() {
		return cantidadManuales;
	}

	public void setCantidadManuales(Integer cantidadManuales) {
		this.cantidadManuales = cantidadManuales;
	}

	public Integer getCantidadRenovadas() {
		return cantidadRenovadas;
	}

	public void setCantidadRenovadas(Integer cantidadRenovadas) {
		this.cantidadRenovadas = cantidadRenovadas;
	}

	public Integer getCantidadModificadas() {
		return cantidadModificadas;
	}

	public void setCantidadModificadas(Integer cantidadModificadas) {
		this.cantidadModificadas = cantidadModificadas;
	}

	public Integer getCantidadAnuladas() {
		return cantidadAnuladas;
	}

	public void setCantidadAnuladas(Integer cantidadAnuladas) {
		this.cantidadAnuladas = cantidadAnuladas;
	}

	public Integer getCantidadNoRenuevan() {
		return cantidadNoRenuevan;
	}

	public void setCantidadNoRenuevan(Integer cantidadNoRenuevan) {
		this.cantidadNoRenuevan = cantidadNoRenuevan;
	}

	public Integer getCantidadNuevas() {
		return cantidadNuevas;
	}

	public void setCantidadNuevas(Integer cantidadNuevas) {
		this.cantidadNuevas = cantidadNuevas;
	}

	public Integer getCantidadTotalPolizas() {
		return cantidadTotalPolizas;
	}

	public void setCantidadTotalPolizas(Integer cantidadTotalPolizas) {
		this.cantidadTotalPolizas = cantidadTotalPolizas;
	}

	public Integer getCantidadFueraPauta() {
		return cantidadFueraPauta;
	}

	public void setCantidadFueraPauta(Integer cantidadFueraPauta) {
		this.cantidadFueraPauta = cantidadFueraPauta;
	}

	public Integer getCantidadEnPauta() {
		return cantidadEnPauta;
	}

	public void setCantidadEnPauta(Integer cantidadEnPauta) {
		this.cantidadEnPauta = cantidadEnPauta;
	}

	public Integer getTotalCotizacionesRenovacion() {
		return totalCotizacionesRenovacion;
	}

	public void setTotalCotizacionesRenovacion(Integer totalCotizacionesRenovacion) {
		this.totalCotizacionesRenovacion = totalCotizacionesRenovacion;
	}

	public Integer getCantidadPolizas() {
		return cantidadPolizas;
	}

	public void setCantidadPolizas(Integer cantidadPolizas) {
		this.cantidadPolizas = cantidadPolizas;
	}

	public Integer getCantidadCotizaciones() {
		return cantidadCotizaciones;
	}

	public void setCantidadCotizaciones(Integer cantidadCotizaciones) {
		this.cantidadCotizaciones = cantidadCotizaciones;
	}

	public Integer getCantidadCotizacionRenovacionModificadas() {
		return cantidadCotizacionRenovacionModificadas;
	}

	public void setCantidadCotizacionRenovacionModificadas(Integer cantidadCotizacionRenovacionModificadas) {
		this.cantidadCotizacionRenovacionModificadas = cantidadCotizacionRenovacionModificadas;
	}

}
