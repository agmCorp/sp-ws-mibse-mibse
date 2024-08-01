package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatoParametrico extends ParamGenerico{
	private Integer numCotizacion;
	private String datoCod;
	private String datoValor;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getDatoCod() {
		return datoCod;
	}
	public void setDatoCod(String datoCod) {
		this.datoCod = datoCod;
	}
	public String getDatoValor() {
		return datoValor;
	}
	public void setDatoValor(String datoValor) {
		this.datoValor = datoValor;
	}	
}
