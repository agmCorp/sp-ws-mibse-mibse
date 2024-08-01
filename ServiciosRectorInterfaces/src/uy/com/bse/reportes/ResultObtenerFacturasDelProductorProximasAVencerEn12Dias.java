package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteFactura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerFacturasDelProductorProximasAVencerEn12Dias extends ResultGenerico {

	private static final long serialVersionUID = 8075376307240255888L;

	private ArrayList<ReporteFactura> datos;

	public ResultObtenerFacturasDelProductorProximasAVencerEn12Dias() {
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
