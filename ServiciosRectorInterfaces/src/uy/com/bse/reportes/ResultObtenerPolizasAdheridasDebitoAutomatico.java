package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReportePolizasAdheridasDebitoAutomatico;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizasAdheridasDebitoAutomatico extends ResultGenerico{
	private static final long serialVersionUID = 3034988944542494144L;

	private ArrayList<ReportePolizasAdheridasDebitoAutomatico> datos;

	public ResultObtenerPolizasAdheridasDebitoAutomatico() {
		this.datos = new ArrayList<ReportePolizasAdheridasDebitoAutomatico>();
	}

	public ArrayList<ReportePolizasAdheridasDebitoAutomatico> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReportePolizasAdheridasDebitoAutomatico> datos) {
		this.datos = datos;
	}

	public void setUno(ReportePolizasAdheridasDebitoAutomatico dato) {
		this.datos.add(dato);
	}
	
}
