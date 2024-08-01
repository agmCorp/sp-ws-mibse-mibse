package uy.com.bse.maestro.personas.domicilio;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosDireccion extends ParamGenerico{
	private Integer codPersona;
	private Integer codDireccion;
	private Integer tipoDireccion;
	private Integer numPostal;
	private String calle;
	private String numeroPuerta;
	private String unidad;
	private String piso;
	private String apto;
	private String padron;
	private String usuario;
	private String aclaraciones;
	
	public Integer getCodDireccion() {
		return codDireccion;
	}
	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public Integer getTipoDireccion() {
		return tipoDireccion;
	}
	public void setTipoDireccion(Integer tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}
	public Integer getNumPostal() {
		return numPostal;
	}
	public void setNumPostal(Integer numPostal) {
		this.numPostal = numPostal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroPuerta() {
		return numeroPuerta;
	}
	public void setNumeroPuerta(String numeroPuerta) {
		this.numeroPuerta = numeroPuerta;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getApto() {
		return apto;
	}
	public void setApto(String apto) {
		this.apto = apto;
	}
	public String getPadron() {
		return padron;
	}
	public void setPadron(String padron) {
		this.padron = padron;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAclaraciones() {
		return aclaraciones;
	}
	public void setAclaraciones(String aclaraciones) {
		this.aclaraciones = aclaraciones;
	}	
}
