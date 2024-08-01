package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteResumenEmision;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerResumenEmision extends ResultGenerico {
	private static final long serialVersionUID = 4287176032373775965L;
	
	private ArrayList<ReporteResumenEmision> datos;

	public ResultObtenerResumenEmision() {
		this.datos = new ArrayList<ReporteResumenEmision>();
	}

	public ArrayList<ReporteResumenEmision> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteResumenEmision> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteResumenEmision dato) {
		this.datos.add(dato);
	}

}
