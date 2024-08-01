package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReportePolizasParaRehabilitar;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores extends ResultGenerico {
	private static final long serialVersionUID = 1146844562604766919L;
	
	private ArrayList<ReportePolizasParaRehabilitar> datos;

	public ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores() {
		this.datos = new ArrayList<ReportePolizasParaRehabilitar>();
	}

	public ArrayList<ReportePolizasParaRehabilitar> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReportePolizasParaRehabilitar> datos) {
		this.datos = datos;
	}

	public void setUno(ReportePolizasParaRehabilitar dato) {
		this.datos.add(dato);
	}

}
