package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarDirCotizacion extends ParamGenerico{
	private Integer numCotizacion;
	private Integer codDireccionCobro;
	private Integer codDireccionEnvio;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getCodDireccionCobro() {
		return codDireccionCobro;
	}
	public void setCodDireccionCobro(Integer codDireccionCobro) {
		this.codDireccionCobro = codDireccionCobro;
	}
	public Integer getCodDireccionEnvio() {
		return codDireccionEnvio;
	}
	public void setCodDireccionEnvio(Integer codDireccionEnvio) {
		this.codDireccionEnvio = codDireccionEnvio;
	}
	
	

}
