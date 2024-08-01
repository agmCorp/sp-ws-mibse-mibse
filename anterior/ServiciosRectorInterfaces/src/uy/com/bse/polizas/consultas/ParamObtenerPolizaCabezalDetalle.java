package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPolizaCabezalDetalle extends ParamGenerico {
	private static final long serialVersionUID = 119505646886123232L;
	private Integer codRamo;
	private Integer numPoliza;
	private String codProductor;
	

	public String getCodProductor() {
		return codProductor;
	}

	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
}
