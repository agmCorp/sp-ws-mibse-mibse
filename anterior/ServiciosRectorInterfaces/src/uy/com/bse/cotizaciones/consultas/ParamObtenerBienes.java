package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerBienes extends ParamGenerico{
	private Integer numSucursal;
	private Integer numCotizacion;
	private Integer numConsecutivo;
	private Integer codRamo;
	private String codProducto;
	
	public Integer getNumSucursal() {
		return numSucursal;
	}
	public void setNumSucursal(Integer numSucursal) {
		this.numSucursal = numSucursal;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}
	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}	
}
