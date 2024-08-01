package uy.com.bse.cotizaciones.lovs;

import java.io.Serializable;

public class ValorDato implements Serializable{
	String codigo;
	String decripcion;
	
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDecripcion() {
		return decripcion;
	}
	public void setDecripcion(String decripcion) {
		this.decripcion = decripcion;
	}
	

}
