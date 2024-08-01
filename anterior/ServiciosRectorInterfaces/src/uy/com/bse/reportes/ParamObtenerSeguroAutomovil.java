package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerSeguroAutomovil extends ParamGenerico{

	private static final long serialVersionUID = 7300300632923321764L;
	private String fechaDesde;
	private String fechaHasta;
	
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
