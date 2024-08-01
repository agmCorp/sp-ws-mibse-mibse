package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class BienAgregar implements Serializable{
	private Integer codBien;
	private String descripBien;
	
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
