package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValidarCodigoAdhesion extends ParamGenerico {
	
	private static final long serialVersionUID = 5926209205720592251L;
	private String codAdhesion;
	
	public String getCodAdhesion() {
		return codAdhesion;
	}
	public void setCodAdhesion(String codAdhesion) {
		this.codAdhesion = codAdhesion;
	}

}
