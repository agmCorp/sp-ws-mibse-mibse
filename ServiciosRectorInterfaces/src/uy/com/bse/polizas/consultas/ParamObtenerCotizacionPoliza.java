package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerCotizacionPoliza extends ParamGenerico{
	private String codRamo;
	private String numPoliza;
	private String numCertificado;
	private String numEndoso;
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(String numEndoso) {
		this.numEndoso = numEndoso;
	}		
	
}
