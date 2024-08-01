package uy.com.bse.maestro.personas.datosbancarios;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamBorrarDatoBancario extends ParamGenerico {
	
	/*  p_nu_persona          IN CART_DOMICILIOS_BANCARIOS.CADM_CABU_NU_PERSONA%TYPE,
        p_nu_consecutivo      IN CART_DOMICILIOS_BANCARIOS.CADM_NU_DOMICILIO%TYPE,
*/
	
	private Integer numCons;
	private Integer codPersona;
	public Integer getNumCons() {
		return numCons;
	}
	public void setNumCons(Integer numCons) {
		this.numCons = numCons;
	}
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}          


}
