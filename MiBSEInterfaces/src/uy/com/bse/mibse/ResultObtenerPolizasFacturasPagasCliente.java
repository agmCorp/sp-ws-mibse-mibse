package uy.com.bse.mibse;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizasFacturasPagasCliente extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;

	ArrayList<DatosFacturacion> datosFacturacion = new ArrayList<DatosFacturacion>();

	public ArrayList<DatosFacturacion> getDatosFacturacion() {
		return datosFacturacion;
	}

	public void setDatosFacturacion(ArrayList<DatosFacturacion> datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}
	
	public void setUno(DatosFacturacion datos){
		this.datosFacturacion.add(datos);
	}

}
