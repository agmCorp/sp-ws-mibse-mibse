package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarFacturacionPoliza extends ParamGenerico {
	private static final long serialVersionUID = 5968870988723327892L;
	private Integer codRamo;
	private Integer numPoliza;
	private Boolean codMarca;

	public Integer getCodRamo() {
		return codRamo;
	}

	public Boolean getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(Boolean codMarca) {
		this.codMarca = codMarca;
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
