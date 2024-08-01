package uy.com.bse.servicios.rector.emision;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamEmision extends ParamGenerico{
	public Integer getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(Integer codProductor) {
		this.codProductor = codProductor;
	}
	private Integer numCotizacion;
	private Integer planPago;
	private Integer codProductor;

	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getPlanPago() {
		return planPago;
	}
	public void setPlanPago(Integer planPago) {
		this.planPago = planPago;
	}	
}
