package uy.com.bse.cotizaciones.operaciones;

import javax.xml.bind.annotation.XmlElement;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCopiarCotizaPoli extends ParamGenerico{
	private Integer numCotizacion;
	private Integer numCotizacionACopiar;
	private Integer numPolizaACopiar;
	
	@XmlElement(nillable=true, required=true)
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getNumCotizacionACopiar() {
		return numCotizacionACopiar;
	}
	public void setNumCotizacionACopiar(Integer numCotizacionACopiar) {
		this.numCotizacionACopiar = numCotizacionACopiar;
	}
	public Integer getNumPolizaACopiar() {
		return numPolizaACopiar;
	}
	public void setNumPolizaACopiar(Integer numPolizaACopiar) {
		this.numPolizaACopiar = numPolizaACopiar;
	}	
}
