package uy.com.bse.recuotificacion;

import java.io.Serializable;

public class CuotaRecuotificacion implements Serializable{
	private Double montoRecargo;
	private Double montoIva;
	private String vencimiento;
	private Double total;
	private Double montoPrima;
	private Double cuotaPura;
	private Double montoOtros;
	
	public Double getMontoRecargo() {
		return montoRecargo;
	}
	public void setMontoRecargo(Double montoRecargo) {
		this.montoRecargo = montoRecargo;
	}
	public Double getMontoIva() {
		return montoIva;
	}
	public void setMontoIva(Double montoIva) {
		this.montoIva = montoIva;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(Double montoPrima) {
		this.montoPrima = montoPrima;
	}
	public Double getCuotaPura() {
		return cuotaPura;
	}
	public void setCuotaPura(Double cuotaPura) {
		this.cuotaPura = cuotaPura;
	}
	public Double getMontoOtros() {
		return montoOtros;
	}
	public void setMontoOtros(Double montoOtros) {
		this.montoOtros = montoOtros;
	}
	
}
