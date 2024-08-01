package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteFactura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerFacturasDelProductorProximasAVencer extends ResultGenerico {

	private static final long serialVersionUID = 2081297104273914012L;

	private ArrayList<ReporteFactura> datos;

	public ResultObtenerFacturasDelProductorProximasAVencer() {
		this.datos = new ArrayList<ReporteFactura>();
	}

	public ArrayList<ReporteFactura> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteFactura> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteFactura dato) {
		this.datos.add(dato);
	}

}
