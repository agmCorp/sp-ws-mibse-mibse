package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerMaximoEndoso extends ParamGenerico {
	private static final long serialVersionUID = -3964291319069425885L;

	private Integer codRamo;
	private Integer numPoliza;

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
