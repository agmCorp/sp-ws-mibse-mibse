package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ReporteLogsUsuariosProductor;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerLogsUsuariosProductor extends ResultGenerico {

	private static final long serialVersionUID = 7989008015029163640L;

	private ArrayList<ReporteLogsUsuariosProductor> datos;

	public ResultObtenerLogsUsuariosProductor() {
		this.datos = new ArrayList<ReporteLogsUsuariosProductor>();
	}

	public ArrayList<ReporteLogsUsuariosProductor> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<ReporteLogsUsuariosProductor> datos) {
		this.datos = datos;
	}

	public void setUno(ReporteLogsUsuariosProductor dato) {
		this.datos.add(dato);
	}

}
