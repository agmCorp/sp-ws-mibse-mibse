package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Pais implements Serializable{
	private String codPais;
	private String descPais;
	
	public String getCodPais() {
		return codPais;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getDescPais() {
		return descPais;
	}
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}
}
