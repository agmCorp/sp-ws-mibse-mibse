package uy.com.bse.afiliaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAfiliacionCuenta extends ResultGenerico {
	private static final long serialVersionUID = 2335834236705089113L;
	private AfiliacionCuenta afiliacionCuenta;

	public AfiliacionCuenta getAfiliacionCuenta() {
		return afiliacionCuenta;
	}

	public void setAfiliacionCuenta(AfiliacionCuenta afiliacionCuenta) {
		this.afiliacionCuenta = afiliacionCuenta;
	}
}
