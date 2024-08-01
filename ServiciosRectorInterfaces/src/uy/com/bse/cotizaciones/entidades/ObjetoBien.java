package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class ObjetoBien implements Serializable{
	private Integer codObjeto;
	private String descripcionObjeto;
	private String descripcion;
	private Double valor;
	private Integer numUnidades;
	private Double valorTotal;
	private Integer codMarca;
	private String descripcionMarca;
	private String serial;
	private Integer numOrdinal;
	
	public Integer getCodObjeto() {
		return codObjeto;
	}
	public void setCodObjeto(Integer codObjeto) {
		this.codObjeto = codObjeto;
	}
	public String getDescripcionObjeto() {
		return descripcionObjeto;
	}
	public void setDescripcionObjeto(String descripcionObjeto) {
		this.descripcionObjeto = descripcionObjeto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getNumUnidades() {
		return numUnidades;
	}
	public void setNumUnidades(Integer numUnidades) {
		this.numUnidades = numUnidades;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getCodMarca() {
		return codMarca;
	}
	public void setCodMarca(Integer codMarca) {
		this.codMarca = codMarca;
	}
	public String getDescripcionMarca() {
		return descripcionMarca;
	}
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public Integer getNumOrdinal() {
		return numOrdinal;
	}
	public void setNumOrdinal(Integer numOrdinal) {
		this.numOrdinal = numOrdinal;
	}

}
