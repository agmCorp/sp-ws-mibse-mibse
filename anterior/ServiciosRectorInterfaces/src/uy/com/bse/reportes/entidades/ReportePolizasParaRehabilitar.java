package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReportePolizasParaRehabilitar implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numPoliza;
	private Integer codRamo;
	private Integer certificado;
	private String razonSocial;
	private String moneda;
	private String vencimiento;
	private Double componente;
	private String factura;
	private String segundoVencimiento;

	public ReportePolizasParaRehabilitar(Integer numPoliza, Integer codRamo, Integer certificado, String razonSocial, String moneda, String vencimiento, Double componente, String factura,
			String segundoVencimiento) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.certificado = certificado;
		this.razonSocial = razonSocial;
		this.moneda = moneda;
		this.vencimiento = vencimiento;
		this.componente = componente;
		this.factura = factura;
		this.segundoVencimiento = segundoVencimiento;
	}

	public ReportePolizasParaRehabilitar() {
		super();
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getSegundoVencimiento() {
		return segundoVencimiento;
	}

	public void setSegundoVencimiento(String segundoVencimiento) {
		this.segundoVencimiento = segundoVencimiento;
	}

	public Double getComponente() {
		return componente;
	}

	public void setComponente(Double componente) {
		this.componente = componente;
	}

}
