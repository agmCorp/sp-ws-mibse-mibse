package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Localidad implements Serializable{
	private String codMunicipio;
	private String descMunicipio;
	
	public String getCodMunicipio() {
		return codMunicipio;
	}
	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}
	public String getDescMunicipio() {
		return descMunicipio;
	}
	public void setDescMunicipio(String descMunicipio) {
		this.descMunicipio = descMunicipio;
	}
}
