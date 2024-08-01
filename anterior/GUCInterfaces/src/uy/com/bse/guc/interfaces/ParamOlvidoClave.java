package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamOlvidoClave extends ParamGenerico {

	private static final long serialVersionUID = -283993000782290193L;
	
	private Integer tipoClave =GUCValues.CLAVERESET;
	
	private String claveNueva;
 
	private String email;

	public Integer getTipoClave() {
		return tipoClave;
	}

	public void setTipoClave(Integer tipoClave) {
		this.tipoClave = tipoClave;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
