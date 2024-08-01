package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValidarDetectarEndoso extends ParamGenerico{
	
	private Integer numRamo;
	private Integer numPoliza;
	private Integer numCertificado;
	private String fechaDesde;
	public Integer getNumRamo() {
		return numRamo;
	}
	public void setNumRamo(Integer numRamo) {
		this.numRamo = numRamo;
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
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	
		
}
