package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDetalleTextoPoliza extends ParamGenerico{
	private String numPoliza;
	private String codRamo;
	private String numCertificado;
	private String numConsecutivoTexto;
	private String codTexto;
	
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	
	public String getNumConsecutivoTexto() {
		return numConsecutivoTexto;
	}
	public void setNumConsecutivoTexto(String numConsecutivoTexto) {
		this.numConsecutivoTexto = numConsecutivoTexto;
	}
	public String getCodTexto() {
		return codTexto;
	}
	public void setCodTexto(String codTexto) {
		this.codTexto = codTexto;
	}
	
}
