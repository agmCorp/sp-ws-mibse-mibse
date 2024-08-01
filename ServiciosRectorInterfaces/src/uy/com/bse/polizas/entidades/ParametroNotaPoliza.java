package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class ParametroNotaPoliza implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String etiqueta;
	private String codigo;
	private String descripcion;
	
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
}
