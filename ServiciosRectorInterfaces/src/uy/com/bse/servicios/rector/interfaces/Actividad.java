package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Actividad implements Serializable{
	private String codActividad;
	private String descActividad;
	
	public String getCodActividad() {
		return codActividad;
	}
	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}
	public String getDescActividad() {
		return descActividad;
	}
	public void setDescActividad(String descActividad) {
		this.descActividad = descActividad;
	}
}
