package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.ConsultaExportable;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerConsultasExportables extends ResultGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6084683530158999878L;
	private ArrayList<ConsultaExportable> consultasExportables;

	public ResultObtenerConsultasExportables() {
		this.consultasExportables = new ArrayList<ConsultaExportable>();
	}
	

	public ArrayList<ConsultaExportable> getConsultasExportables() {
		return consultasExportables;
	}




	public void setConsultasExportables(
			ArrayList<ConsultaExportable> consultasExportables) {
		this.consultasExportables = consultasExportables;
	}




	public void setUno(ConsultaExportable consultasExportable) {
		consultasExportables.add(consultasExportable);
	}

}
