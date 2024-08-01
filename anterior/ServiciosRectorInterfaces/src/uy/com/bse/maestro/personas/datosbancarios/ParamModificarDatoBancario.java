package uy.com.bse.maestro.personas.datosbancarios;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamModificarDatoBancario extends ParamGenerico {
	
	/*  p_nu_consecutivo      IN OUT CART_DOMICILIOS_BANCARIOS.CADM_NU_DOMICILIO%TYPE,
      p_nu_persona          IN CART_DOMICILIOS_BANCARIOS.CADM_CABU_NU_PERSONA%TYPE,
      p_cd_banco            IN CART_DOMICILIOS_BANCARIOS.CADM_CABA_CD_BANCO%TYPE,
      p_tp_cuenta           IN CART_DOMICILIOS_BANCARIOS.CADM_TP_CUENTA%TYPE,
      p_tp_tarjeta          IN CART_DOMICILIOS_BANCARIOS.CADM_CATT_TP_TARJETA%TYPE,
      p_nu_cuenta           IN CART_DOMICILIOS_BANCARIOS.CADM_NU_CUENTA%TYPE,
      p_fe_vencimiento      IN CART_DOMICILIOS_BANCARIOS.CADM_FE_VENCIMIENTO%TYPE
*/
	
	private Integer numCons;
	private Integer codPersona;          
    private Integer codBanco;            
    private Integer tipoCuenta;           
    private Integer tipoTarjeta;          
    private String numcuenta;           
    private String fechaVencimiento;
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
	public Integer getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(Integer codBanco) {
		this.codBanco = codBanco;
	}
	public Integer getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(Integer tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Integer getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(Integer tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getNumcuenta() {
		return numcuenta;
	}
	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
    

}
