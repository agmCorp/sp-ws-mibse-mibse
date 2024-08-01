package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteADTBPS;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerListadoControlADTBPSParaProductor extends ResultGenerico {
	private static final long serialVersionUID = -7512894317442509473L;

	private ArrayList<ReporteADTBPS> datos;

	public ResultObtenerListadoControlADTBPSParaProductor() {
		this.datos = new ArrayList<ReporteADTBPS>();
	}

	public ArrayList<ReporteADTBPS> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteADTBPS> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteADTBPS dato) {
		this.datos.add(dato);
	}

}
