package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDetallesMontosSiniestros extends ParamGenerico {
	private static final long serialVersionUID = -4876441541370668053L;
	
	private Integer persona;
	private Integer codRamo;
	private Integer numPoliza;
	private String nivelDeDetalle;

	public Integer getPersona() {
		return persona;
	}

	public void setPersona(Integer persona) {
		this.persona = persona;
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

	public String getNivelDeDetalle() {
		return nivelDeDetalle;
	}

	public void setNivelDeDetalle(String nivelDeDetalle) {
		this.nivelDeDetalle = nivelDeDetalle;
	}
}
