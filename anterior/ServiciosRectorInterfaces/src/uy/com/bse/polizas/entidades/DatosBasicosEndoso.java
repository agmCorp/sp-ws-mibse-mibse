package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class DatosBasicosEndoso implements Serializable{
	private Integer numEndoso;
	private Integer codMotivo;
	private String descripMotivo;
	private Integer numCotizacion;
	private String codPlanCobertura;
	private String descripPlanCobertura;
	private Integer numRefacturacion;
	
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getCodMotivo() {
		return codMotivo;
	}
	public void setCodMotivo(Integer codMotivo) {
		this.codMotivo = codMotivo;
	}
	public String getDescripMotivo() {
		return descripMotivo;
	}
	public void setDescripMotivo(String descripMotivo) {
		this.descripMotivo = descripMotivo;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public String getDescripPlanCobertura() {
		return descripPlanCobertura;
	}
	public void setDescripPlanCobertura(String descripPlanCobertura) {
		this.descripPlanCobertura = descripPlanCobertura;
	}
	public Integer getNumRefacturacion() {
		return numRefacturacion;
	}
	public void setNumRefacturacion(Integer numRefacturacion) {
		this.numRefacturacion = numRefacturacion;
	}

}
