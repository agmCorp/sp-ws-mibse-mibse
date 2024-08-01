package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerListadoControlADTBPSParaProductor extends ParamGenerico {
	private static final long serialVersionUID = -8003263455428750388L;
	private String fecha;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
