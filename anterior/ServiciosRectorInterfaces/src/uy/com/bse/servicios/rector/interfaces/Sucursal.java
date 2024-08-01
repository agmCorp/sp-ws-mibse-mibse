package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Sucursal implements Serializable{
	private String codSucursal;
	private String nomSucursal;
	private String descSucursal;
	
	public String getNomSucursal() {
		return nomSucursal;
	}
	public void setNomSucursal(String nomSucursal) {
		this.nomSucursal = nomSucursal;
	}
	
	public String getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getDescSucursal() {
		return descSucursal;
	}
	public void setDescSucursal(String descSucursal) {
		this.descSucursal = descSucursal;
	}
}
