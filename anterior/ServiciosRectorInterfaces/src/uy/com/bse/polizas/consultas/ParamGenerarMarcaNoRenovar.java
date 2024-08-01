package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGenerarMarcaNoRenovar extends ParamGenerico{
	
	
	private String numPoliza;
	private String codRamo;
	private String fechaDesde;
	private String codOrigen;
	private String codMotAbandono;
	private String obsAbandono;
	
	
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public String getCodMotAbandono() {
		return codMotAbandono;
	}
	public void setCodMotAbandono(String codMotAbandono) {
		this.codMotAbandono = codMotAbandono;
	}
	public String getObsAbandono() {
		return obsAbandono;
	}
	public void setObsAbandono(String obsAbandono) {
		this.obsAbandono = obsAbandono;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}

}
