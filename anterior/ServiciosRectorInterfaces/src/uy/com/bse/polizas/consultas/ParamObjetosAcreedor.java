package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObjetosAcreedor extends ParamGenerico{

	private static final long serialVersionUID = 1L;
	private String sucursal;
	private String numCertificado;
	private String codBienAsegurado;
	private String numPoliza;
	private String codRamo;
	
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCodBienAsegurado() {
		return codBienAsegurado;
	}
	public void setCodBienAsegurado(String codBienAsegurado) {
		this.codBienAsegurado = codBienAsegurado;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
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

}
