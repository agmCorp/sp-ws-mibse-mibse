package uy.com.bse.usuarios.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamConsultaAuditoriaDetalle extends ParamGenerico {
	private String fechaIni;
	private String fechaFin;

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
