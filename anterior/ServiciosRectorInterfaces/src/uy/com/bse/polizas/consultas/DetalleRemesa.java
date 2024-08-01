package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class DetalleRemesa implements Serializable{
	private Integer codTipoMovimiento;
	private String descripTipoMovimiento;
	private String codMoneda;
	private String descripMoneda;
	private Integer numBoleta;
	private String fecha;
	private String posdatado;
	private Integer codBanco;
	private String descripBanco;
	private Integer numCheque;
	private Double montoCheque;
	
	public Integer getCodTipoMovimiento() {
		return codTipoMovimiento;
	}
	public void setCodTipoMovimiento(Integer codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}
	public String getDescripTipoMovimiento() {
		return descripTipoMovimiento;
	}
	public void setDescripTipoMovimiento(String descripTipoMovimiento) {
		this.descripTipoMovimiento = descripTipoMovimiento;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public Integer getNumBoleta() {
		return numBoleta;
	}
	public void setNumBoleta(Integer numBoleta) {
		this.numBoleta = numBoleta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPosdatado() {
		return posdatado;
	}
	public void setPosdatado(String posdatado) {
		this.posdatado = posdatado;
	}
	public Integer getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(Integer codBanco) {
		this.codBanco = codBanco;
	}
	public String getDescripBanco() {
		return descripBanco;
	}
	public void setDescripBanco(String descripBanco) {
		this.descripBanco = descripBanco;
	}
	public Integer getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(Integer numCheque) {
		this.numCheque = numCheque;
	}
	public Double getMontoCheque() {
		return montoCheque;
	}
	public void setMontoCheque(Double montoCheque) {
		this.montoCheque = montoCheque;
	}

}
