package uy.com.bse.mibse;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarFacturacionPolizaTodo extends ParamGenerico {
	private static final long serialVersionUID = 5968870988723327892L;
	private ArrayList<DatosFacturacion> datosFacturacions = new ArrayList<DatosFacturacion>();

	public final ArrayList<DatosFacturacion> getDatosFacturacions() {
		return datosFacturacions;
	}

	public final void setDatosFacturacions(ArrayList<DatosFacturacion> datosFacturacions) {
		this.datosFacturacions = datosFacturacions;
	}
}
