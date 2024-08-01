package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerOrigenRemesaAuto extends ParamGenerico{
	private String numRemesa;

	public String getNumRemesa() {
		return numRemesa;
	}

	public void setNumRemesa(String numRemesa) {
		this.numRemesa = numRemesa;
	}

}
