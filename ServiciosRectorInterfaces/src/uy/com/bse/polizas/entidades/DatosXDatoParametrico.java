package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class DatosXDatoParametrico implements Serializable{
	
	private Integer numCertificado;
	private String cliente;
	private Integer codBien;
	private String descBien;
	private Integer numEndoso;
	
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Integer getCodBien() {
		return codBien;
	}
	public void setCodBien(Integer codBien) {
		this.codBien = codBien;
	}
	public String getDescBien() {
		return descBien;
	}
	public void setDescBien(String descBien) {
		this.descBien = descBien;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}	

}
