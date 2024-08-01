package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Organismo implements Serializable{
	private String tipoOrganismo;
	private String descOrganismo;
	
	public String getTipoOrganismo() {
		return tipoOrganismo;
	}
	public void setTipoOrganismo(String tipoOrganismo) {
		this.tipoOrganismo = tipoOrganismo;
	}
	public String getDescOrganismo() {
		return descOrganismo;
	}
	public void setDescOrganismo(String descOrganismo) {
		this.descOrganismo = descOrganismo;
	}
}
