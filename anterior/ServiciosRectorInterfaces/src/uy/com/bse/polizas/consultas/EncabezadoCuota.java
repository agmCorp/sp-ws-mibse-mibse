package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class EncabezadoCuota implements Serializable{
	private Integer codRamo; 
	private Integer numPoliza; 
	private Integer numCertificado;
	private String codMoneda;
	private String descripMoneda; 
	private String cliente;
	
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
