package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;


public class ParamCambioClave extends ParamGenerico{

	private static final long serialVersionUID = -8346937641565909747L;
	private String claveNueva;
	private String claveAnterior;

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	public String getClaveAnterior() {
		return claveAnterior;
	}

	public void setClaveAnterior(String claveAnterior) {
		this.claveAnterior = claveAnterior;
	}

}
