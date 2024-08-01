package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteIncentivoPlusTotal;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerIncentivoPlusTotales extends ResultGenerico {
	private static final long serialVersionUID = 4287176032373775965L;

	private ArrayList<ReporteIncentivoPlusTotal> datos;

	public ResultObtenerIncentivoPlusTotales() {
		this.datos = new ArrayList<ReporteIncentivoPlusTotal>();
	}

	public ArrayList<ReporteIncentivoPlusTotal> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteIncentivoPlusTotal> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteIncentivoPlusTotal dato) {
		this.datos.add(dato);
	}

}
