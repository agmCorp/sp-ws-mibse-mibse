package uy.com.bse.afiliaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerBancoAfiliacionCuenta extends ParamGenerico {
	private static final long serialVersionUID = -594378780163175361L;
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
