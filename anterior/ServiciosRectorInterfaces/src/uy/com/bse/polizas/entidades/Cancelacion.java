package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Cancelacion implements Serializable {

	private static final long serialVersionUID = -1653248008347002332L;
	private Integer numCancelacion;
	private String codCancelacion;
	private String descripMoneda;
	private Double montoCancelado;
	private String fechaActualizacion;
	private String codUsuario;
	private Integer numLiquidacion;
	
	public Integer getNumCancelacion() {
		return numCancelacion;
	}
	public void setNumCancelacion(Integer numCancelacion) {
		this.numCancelacion = numCancelacion;
	}
	public String getCodCancelacion() {
		return codCancelacion;
	}
	public void setCodCancelacion(String codCancelacion) {
		this.codCancelacion = codCancelacion;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public Double getMontoCancelado() {
		return montoCancelado;
	}
	public void setMontoCancelado(Double montoCancelado) {
		this.montoCancelado = montoCancelado;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public Integer getNumLiquidacion() {
		return numLiquidacion;
	}
	public void setNumLiquidacion(Integer numLiquidacion) {
		this.numLiquidacion = numLiquidacion;
	}

}
