package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class Acreedor implements Serializable{
	private Integer codigo;
	private String nombreAcreedor;
	private Integer numPersona;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombreAcreedor() {
		return nombreAcreedor;
	}
	public void setNombreAcreedor(String nombreAcreedor) {
		this.nombreAcreedor = nombreAcreedor;
	}
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
		

}
