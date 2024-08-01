package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGenerarEndoso extends ParamGenerico{
	
	private String numRamo; 
	private String numPoliza;
	private String numCertificado;
	private String numEndoso;
	private String codOrigen;
	private String fechaDesde;
	
	
	
	public String getNumRamo() {
		return numRamo;
	}
	public void setNumRamo(String numRamo) {
		this.numRamo = numRamo;
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
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	
	

}
