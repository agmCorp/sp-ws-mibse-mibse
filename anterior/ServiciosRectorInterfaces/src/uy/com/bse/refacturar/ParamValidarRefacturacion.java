package uy.com.bse.refacturar;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValidarRefacturacion extends ParamGenerico{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7536472007219725442L;
	private String numPoliza;
	private String codRamo;

	
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	
}
