package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCartaXTipoCarta extends ParamGenerico {
	private static final long serialVersionUID = -6154166122121431145L;

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
