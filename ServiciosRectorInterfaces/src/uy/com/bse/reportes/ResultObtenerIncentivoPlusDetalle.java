package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteIncentivoPlusDetalle;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerIncentivoPlusDetalle extends ResultGenerico {

	private static final long serialVersionUID = 300801567471030839L;

	private ArrayList<ReporteIncentivoPlusDetalle> datos;

	public ResultObtenerIncentivoPlusDetalle() {
		this.datos = new ArrayList<ReporteIncentivoPlusDetalle>();
	}

	public ArrayList<ReporteIncentivoPlusDetalle> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteIncentivoPlusDetalle> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteIncentivoPlusDetalle dato) {
		this.datos.add(dato);
	}

}
