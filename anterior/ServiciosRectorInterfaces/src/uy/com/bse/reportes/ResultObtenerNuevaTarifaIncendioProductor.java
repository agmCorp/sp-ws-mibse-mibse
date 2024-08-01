package uy.com.bse.reportes;

import java.util.ArrayList;



import uy.com.bse.reportes.entidades.ReporteNuevaTarifaIncendioProductor;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerNuevaTarifaIncendioProductor extends ResultGenerico {

	private static final long serialVersionUID = 2356758876663467930L;
	private ArrayList<ReporteNuevaTarifaIncendioProductor> datos;

	
	
	public ResultObtenerNuevaTarifaIncendioProductor() {
		this.datos = new ArrayList<ReporteNuevaTarifaIncendioProductor>();
	}
	
	
	public ArrayList<ReporteNuevaTarifaIncendioProductor> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<ReporteNuevaTarifaIncendioProductor> datos) {
		this.datos = datos;
	}
	
	public void setUno(ReporteNuevaTarifaIncendioProductor dato) {
		this.datos.add(dato);
	}

	
}
