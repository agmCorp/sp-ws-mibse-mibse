package uy.com.bse.afiliaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerBancosAfiliacionCuenta extends ResultGenerico {
	private static final long serialVersionUID = -2614434046170082082L;

	private ArrayList<BancoAfiliacionCuenta> bancosAfiliacionCuenta = new ArrayList<BancoAfiliacionCuenta>();

	public ArrayList<BancoAfiliacionCuenta> getBancosAfiliacionCuenta() {
		return bancosAfiliacionCuenta;
	}

	public void setBancosAfiliacionCuenta(ArrayList<BancoAfiliacionCuenta> bancosAfiliacionCuenta) {
		this.bancosAfiliacionCuenta = bancosAfiliacionCuenta;
	}
}
