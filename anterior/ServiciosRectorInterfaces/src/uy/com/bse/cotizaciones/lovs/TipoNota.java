package uy.com.bse.cotizaciones.lovs;

import java.io.Serializable;

public class TipoNota implements Serializable{
	private String codigo;
	private String descripcion;
	private String tieneLov;
	
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
	public String getTieneLov() {
		return tieneLov;
	}
	public void setTieneLov(String tieneLov) {
		this.tieneLov = tieneLov;
	}
		
	
}
