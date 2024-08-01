package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamListaBancos extends ParamGenerico{
	private String numPersona;
	private String codMedioPago;
	private String codOrigenPago;
	
	public String getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(String numPersona) {
		this.numPersona = numPersona;
	}
	public String getCodMedioPago() {
		return codMedioPago;
	}
	public void setCodMedioPago(String codMedioPago) {
		this.codMedioPago = codMedioPago;
	}
	public String getCodOrigenPago() {
		return codOrigenPago;
	}
	public void setCodOrigenPago(String codOrigenPago) {
		this.codOrigenPago = codOrigenPago;
	}
	
}
