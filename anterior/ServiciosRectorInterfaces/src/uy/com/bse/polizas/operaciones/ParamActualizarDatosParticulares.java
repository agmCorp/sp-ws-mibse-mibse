package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarDatosParticulares extends ParamGenerico{

	private String numRamo; 
	private String numPoliza;
	private String numCertificado;
	private String numEndoso;
	private String codBien;
	private String codDato;
	private String codValor;
	
	
	public String getNumRamo() {
		return numRamo;
	}
	public void setNumRamo(String numRamo) {
		this.numRamo = numRamo;
	}
	public String getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(String numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getCodBien() {
		return codBien;
	}
	public void setCodBien(String codBien) {
		this.codBien = codBien;
	}
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	public String getCodValor() {
		return codValor;
	}
	public void setCodValor(String codValor) {
		this.codValor = codValor;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}

	
	

	
	

}
