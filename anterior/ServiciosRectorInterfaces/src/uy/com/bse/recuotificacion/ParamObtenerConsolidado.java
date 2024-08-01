package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerConsolidado extends ParamGenerico{
	private String codRamo;
	private String numPoliza;
	private String numCertificado;
	
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


}
