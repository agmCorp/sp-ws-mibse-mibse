package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGuardarImprimirCarta extends ParamGenerico {

	private static final long serialVersionUID = 168903133846491781L;
	
	private String codRamo;
	private String numPoliza;
	private String numCertificado;
	private String numCarta;
	private String numReporte;
	private String codReporte;
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
	public String getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(String numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumCarta() {
		return numCarta;
	}
	public void setNumCarta(String numCarta) {
		this.numCarta = numCarta;
	}
	public String getNumReporte() {
		return numReporte;
	}
	public void setNumReporte(String numReporte) {
		this.numReporte = numReporte;
	}
	public String getCodReporte() {
		return codReporte;
	}
	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}
	
}
