package uy.com.bse.cotizaciones.lovs;

import java.io.Serializable;

public class TipoAcreedor implements Serializable{
	private String tipo;
	private String descripcion;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
