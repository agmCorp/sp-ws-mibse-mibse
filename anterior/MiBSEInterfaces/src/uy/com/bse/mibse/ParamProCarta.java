package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamProCarta extends ParamGenerico {

	private static final long serialVersionUID = 8264209137521255944L;
	
	private int ramo;
	private String poliza;

	public int getRamo() {
		return ramo;
	}

	public void setRamo(int ramo) {
		this.ramo = ramo;
	}
	
	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	
}
