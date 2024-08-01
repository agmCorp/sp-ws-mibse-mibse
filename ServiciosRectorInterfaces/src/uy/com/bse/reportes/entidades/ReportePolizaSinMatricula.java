package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReportePolizaSinMatricula implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numPoliza;
	private Integer codRamo;
	private Integer certificado;
	private String razonSocial;
	private String numCliente;

	public ReportePolizaSinMatricula(Integer numPoliza, Integer codRamo, Integer certificado, String razonSocial, String numCliente) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.certificado = certificado;
		this.razonSocial = razonSocial;
		this.numCliente = numCliente;
	}

	public ReportePolizaSinMatricula() {
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

	public String getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}

}
