package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamNuevaCotizacionEndoso extends ParamGenerico{
	private String codRamo;
	private String codProducto;
	private String numPoliza;
	private String codOrigenEndoso;
	private String fechaDesdeVigencia;
	private Integer codDireccionCobro;
	private Integer codDireccionEnvio;
	private Integer numCertificado;
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodOrigenEndoso() {
		return codOrigenEndoso;
	}
	public void setCodOrigenEndoso(String codOrigenEndoso) {
		this.codOrigenEndoso = codOrigenEndoso;
	}
	public String getFechaDesdeVigencia() {
		return fechaDesdeVigencia;
	}
	public void setFechaDesdeVigencia(String fechaDesdeVigencia) {
		this.fechaDesdeVigencia = fechaDesdeVigencia;
	}
	public Integer getCodDireccionCobro() {
		return codDireccionCobro;
	}
	public void setCodDireccionCobro(Integer codDireccionCobro) {
		this.codDireccionCobro = codDireccionCobro;
	}
	public Integer getCodDireccionEnvio() {
		return codDireccionEnvio;
	}
	public void setCodDireccionEnvio(Integer codDireccionEnvio) {
		this.codDireccionEnvio = codDireccionEnvio;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}	
	

}
