package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class EncabezadoCotizacion implements Serializable{
	private Integer codRamo;
	private String descripRamo;
	private Integer numCotizacion;
	private Integer numCertificado;
	private String codProducto;
	private String descripProducto;
	private String codNacionalidad;
	private String codCliente;
	private String cliente;
	private Integer codBien;
	private String descripBien;
	
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getDescripRamo() {
		return descripRamo;
	}
	public void setDescripRamo(String descripRamo) {
		this.descripRamo = descripRamo;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getDescripProducto() {
		return descripProducto;
	}
	public void setDescripProducto(String descripProducto) {
		this.descripProducto = descripProducto;
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Integer getCodBien() {
		return codBien;
	}
	public void setCodBien(Integer codBien) {
		this.codBien = codBien;
	}
	public String getDescripBien() {
		return descripBien;
	}
	public void setDescripBien(String descripBien) {
		this.descripBien = descripBien;
	}
	
}
