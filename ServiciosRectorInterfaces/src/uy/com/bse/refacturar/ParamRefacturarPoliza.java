package uy.com.bse.refacturar;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamRefacturarPoliza extends ParamGenerico{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2650645024396791185L;
	private String numPoliza;
	private String codRamo;
	private String soloCotiza;
	private String listaDatosParametricos;
	
	public String getSoloCotiza() {
		return soloCotiza;
	}
	public void setSoloCotiza(String soloCotiza) {
		this.soloCotiza = soloCotiza;
	}
	public String getListaDatosParametricos() {
		return listaDatosParametricos;
	}
	public void setListaDatosParametricos(String listaDatosParametricos) {
		this.listaDatosParametricos = listaDatosParametricos;
	}
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
