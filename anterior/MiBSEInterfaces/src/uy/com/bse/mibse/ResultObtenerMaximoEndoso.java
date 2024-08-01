package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerMaximoEndoso extends ResultGenerico {
	private static final long serialVersionUID = 4906537337311341721L;
	
	private Integer maximoEndoso;

	public Integer getMaximoEndoso() {
		return maximoEndoso;
	}

	public void setMaximoEndoso(Integer maximoEndoso) {
		this.maximoEndoso = maximoEndoso;
	}
}
