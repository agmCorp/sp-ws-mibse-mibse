package uy.com.bse.recuotificacion;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarDatosBancariosEndoso extends ParamGenerico{
	private static final long serialVersionUID = 1L;
	private Integer numDomicilioBanco;
	private Integer numRamo; 
	private Integer numPoliza;
	private Integer numEndoso;
	private Integer codMedioPago;
	private String codOrigenPago;
	
	public Integer getNumDomicilioBanco() {
		return numDomicilioBanco;
	}
	public void setNumDomicilioBanco(Integer numDomicilioBanco) {
		this.numDomicilioBanco = numDomicilioBanco;
	}
	public Integer getNumRamo() {
		return numRamo;
	}
	public void setNumRamo(Integer numRamo) {
		this.numRamo = numRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getCodMedioPago() {
		return codMedioPago;
	}
	public void setCodMedioPago(Integer codMedioPago) {
		this.codMedioPago = codMedioPago;
	}
	public String getCodOrigenPago() {
		return codOrigenPago;
	}
	public void setCodOrigenPago(String codOrigenPago) {
		this.codOrigenPago = codOrigenPago;
	}
	
	
}
