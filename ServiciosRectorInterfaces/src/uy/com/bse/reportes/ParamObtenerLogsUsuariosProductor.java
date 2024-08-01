package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerLogsUsuariosProductor extends ParamGenerico{
	private static final long serialVersionUID = 3599844942290759770L;
	private Integer codRamo;
	private Integer numPoliza;
	private String fechaDesde;
	private String fechaHasta;

	public Integer getCodRamo() {
		return codRamo;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
