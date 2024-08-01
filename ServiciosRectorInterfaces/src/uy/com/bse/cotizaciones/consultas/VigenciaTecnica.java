package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class VigenciaTecnica implements Serializable{
	String codigo;
	String descripcion;
	
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
