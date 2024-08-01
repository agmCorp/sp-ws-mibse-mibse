package uy.com.bse.cotizaciones.operaciones;

import javax.xml.bind.annotation.XmlElement;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAgregarCertificado extends ParamGenerico{
	private Integer numCotizacion;

	
	@XmlElement(nillable=true, required=true)
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}


}
