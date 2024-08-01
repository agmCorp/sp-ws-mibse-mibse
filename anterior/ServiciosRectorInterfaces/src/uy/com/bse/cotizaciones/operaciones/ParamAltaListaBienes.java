package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAltaListaBienes extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String numConsecutivoBien;
	private String codObjeto;
	private String descripcion;
	private String valor;
	private String numUnidades;
	private String codMarca;
	private String serial;
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumConsecutivoBien() {
		return numConsecutivoBien;
	}
	public void setNumConsecutivoBien(String numConsecutivoBien) {
		this.numConsecutivoBien = numConsecutivoBien;
	}
	public String getCodObjeto() {
		return codObjeto;
	}
	public void setCodObjeto(String codObjeto) {
		this.codObjeto = codObjeto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getNumUnidades() {
		return numUnidades;
	}
	public void setNumUnidades(String numUnidades) {
		this.numUnidades = numUnidades;
	}
	public String getCodMarca() {
		return codMarca;
	}
	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
}
