package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class Resumen implements Serializable {

	private static final long serialVersionUID = 6322929892373566263L;
	
	private Integer codTipo;
	private Integer codProductor;
	private String descMovimiento;
	private String tipoMovimiento;
	private String periodo;
	private Integer codMoneda;
	private String descMoneda;
	private Integer cantTipoMovs;
    private Double montoDebito;
    private Double montoCredito;
    private Double montoSaldo;
	
	public Integer getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}
	
	public String getDescMovimiento() {
		return descMovimiento;
	}
	public Integer getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(Integer codProductor) {
		this.codProductor = codProductor;
	}
	public void setDescMovimiento(String descMovimiento) {
		this.descMovimiento = descMovimiento;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public Integer getCantTipoMovs() {
		return cantTipoMovs;
	}
	public void setCantTipoMovs(Integer cantTipoMovs) {
		this.cantTipoMovs = cantTipoMovs;
	}
	public Double getMontoDebito() {
		return montoDebito;
	}
	public void setMontoDebito(Double montoDebito) {
		this.montoDebito = montoDebito;
	}
	public Double getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(Double montoCredito) {
		this.montoCredito = montoCredito;
	}
	public Double getMontoSaldo() {
		return montoSaldo;
	}
	public void setMontoSaldo(Double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}
	public Integer getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescMoneda() {
		return descMoneda;
	}
	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}

 
	


}
