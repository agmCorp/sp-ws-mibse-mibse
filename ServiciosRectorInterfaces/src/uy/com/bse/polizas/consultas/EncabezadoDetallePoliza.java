package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class EncabezadoDetallePoliza implements Serializable{
	private Integer codRamo;
	private Integer numPoliza;
	private Integer numEndoso;
	private Integer numCertificado;
	private Integer codEstado;
	private String descripEstado; 
	private String codProducto;
	private String codPlanCobertura;
	private String descripPlanCobertura;
	private Integer codAgencia;
	private String descripAgencia;
	private String anexos;
	private String codNacionalidad;
	private String codCliente;
	private String nombreAsegurado;
	private Integer codBienAsegurado;
	private String descripBienAsegurado;
		
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}
	public String getDescripEstado() {
		return descripEstado;
	}
	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public String getDescripPlanCobertura() {
		return descripPlanCobertura;
	}
	public void setDescripPlanCobertura(String descripPlanCobertura) {
		this.descripPlanCobertura = descripPlanCobertura;
	}
	public Integer getCodAgencia() {
		return codAgencia;
	}
	public void setCodAgencia(Integer codAgencia) {
		this.codAgencia = codAgencia;
	}
	public String getDescripAgencia() {
		return descripAgencia;
	}
	public void setDescripAgencia(String descripAgencia) {
		this.descripAgencia = descripAgencia;
	}
	public String getAnexos() {
		return anexos;
	}
	public void setAnexos(String anexos) {
		this.anexos = anexos;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}	
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getNombreAsegurado() {
		return nombreAsegurado;
	}
	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}
	public Integer getCodBienAsegurado() {
		return codBienAsegurado;
	}
	public void setCodBienAsegurado(Integer codBienAsegurado) {
		this.codBienAsegurado = codBienAsegurado;
	}
	public String getDescripBienAsegurado() {
		return descripBienAsegurado;
	}
	public void setDescripBienAsegurado(String descripBienAsegurado) {
		this.descripBienAsegurado = descripBienAsegurado;
	}

}
