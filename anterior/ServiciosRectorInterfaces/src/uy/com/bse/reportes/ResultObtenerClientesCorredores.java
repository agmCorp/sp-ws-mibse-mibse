package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteClienteCorredor;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerClientesCorredores extends ResultGenerico{

	private static final long serialVersionUID = -5917122128450026188L;
	
	private ArrayList<ReporteClienteCorredor> datos;

	public ResultObtenerClientesCorredores() {
		this.datos = new ArrayList<ReporteClienteCorredor>();
	}

	public ArrayList<ReporteClienteCorredor> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteClienteCorredor> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteClienteCorredor dato) {
		this.datos.add(dato);
	}
	
}
