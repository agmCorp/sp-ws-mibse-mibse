package uy.com.bse.cotizaciones.operaciones;

import javax.xml.bind.annotation.XmlElement;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAnularCertificado extends ParamGenerico{
	private Integer numCotizacion;
	private Integer numConsecutivo;
	
	@XmlElement(nillable=true, required=true)
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	@XmlElement(nillable=true, required=true)
	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}
	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}	
}
