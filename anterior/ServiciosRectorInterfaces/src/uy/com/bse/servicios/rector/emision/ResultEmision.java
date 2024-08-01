package uy.com.bse.servicios.rector.emision;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultEmision extends ResultGenerico{
	private Emision emision;

	public Emision getEmision() {
		return emision;
	}

	public void setEmision(Emision emision) {
		this.emision = emision;
	}
}
