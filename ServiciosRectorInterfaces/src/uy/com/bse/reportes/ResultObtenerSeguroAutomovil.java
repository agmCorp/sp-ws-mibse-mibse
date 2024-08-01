package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteSeguroAutomovil;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerSeguroAutomovil extends ResultGenerico {
	private static final long serialVersionUID = 4287176032373775965L;
	
	private ArrayList<ReporteSeguroAutomovil> datos;

	public ResultObtenerSeguroAutomovil() {
		this.datos = new ArrayList<ReporteSeguroAutomovil>();
	}

	public ArrayList<ReporteSeguroAutomovil> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteSeguroAutomovil> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteSeguroAutomovil dato) {
		this.datos.add(dato);
	}

}
