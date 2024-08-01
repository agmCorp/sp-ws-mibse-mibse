package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReportePolizaSinMatricula;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizasSinMatriculasXProductores extends ResultGenerico {
	private static final long serialVersionUID = -4255477021580365987L;

	private ArrayList<ReportePolizaSinMatricula> datos;

	public ResultObtenerPolizasSinMatriculasXProductores() {
		this.datos = new ArrayList<ReportePolizaSinMatricula>();
	}

	public ArrayList<ReportePolizaSinMatricula> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReportePolizaSinMatricula> datos) {
		this.datos = datos;
	}

	public void setUno(ReportePolizaSinMatricula dato) {
		this.datos.add(dato);
	}

}
