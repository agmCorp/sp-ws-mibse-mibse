package uy.com.bse.autodata.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultConsultaAutodata extends ResultGenerico {
	private static final long serialVersionUID = -8044558850627447827L;
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
