package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;

public class PlanPago implements Serializable{
	
	private String codigo;
	private String descripcion;
	private String formaPago;
	private Boolean errorGenerado;
	private String error;
	private Double montoCuota;
	private Double montoTotal;
	private Double montoPrima;
	private Double montoPremio;
	private Integer numCuotas;
	private Boolean seleccionado;
	private Integer diaVto;
	private String descMoneda;
	private String simboloMoneda;
	private Integer codNivelComision;
	private String descNivelComision;

	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public Boolean getErrorGenerado() {
		return errorGenerado;
	}
	public void setErrorGenerado(Boolean errorGenerado) {
		this.errorGenerado = errorGenerado;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Integer getNumCuotas() {
		return numCuotas;
	}
	public void setNumCuotas(Integer numCuotas) {
		this.numCuotas = numCuotas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Integer getDiaVto() {
		return diaVto;
	}
	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}
	public String getDescMoneda() {
		return descMoneda;
	}
	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}
	public String getSimboloMoneda() {
		return simboloMoneda;
	}
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}
	public Integer getCodNivelComision() {
		return codNivelComision;
	}
	public void setCodNivelComision(Integer codNivelComision) {
		this.codNivelComision = codNivelComision;
	}
	public String getDescNivelComision() {
		return descNivelComision;
	}
	public void setDescNivelComision(String descNivelComision) {
		this.descNivelComision = descNivelComision;
	}
	public Double getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(Double montoPrima) {
		this.montoPrima = montoPrima;
	}
	public Double getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(Double montoPremio) {
		this.montoPremio = montoPremio;
	}
			
}
