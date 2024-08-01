package uy.com.bse.maestro.personas.consultas;

import java.io.Serializable;

public class Rol implements Serializable{
	private Integer tipoRol;
	private String descripRol;
	
	public Integer getTipoRol() {
		return tipoRol;
	}
	public void setTipoRol(Integer tipoRol) {
		this.tipoRol = tipoRol;
	}
	public String getDescripRol() {
		return descripRol;
	}
	public void setDescripRol(String descripRol) {
		this.descripRol = descripRol;
	}	
}
