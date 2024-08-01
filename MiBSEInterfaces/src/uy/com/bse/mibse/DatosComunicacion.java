package uy.com.bse.mibse;

import java.io.Serializable;

public class DatosComunicacion implements Serializable{
	
	private static final long serialVersionUID = 8338890693998704816L;
	private Integer numPersona;
	private Integer codComunicacion;
	private Integer tipoComunicacion;
	private String valorComunicacion;
	private String fechaActualizacion;
	private String validada;
	
	public Integer getCodComunicacion() {
		return codComunicacion;
	}
	public void setCodComunicacion(Integer codComunicacion) {
		this.codComunicacion = codComunicacion;
	}
	public Integer getTipoComunicacion() {
		return tipoComunicacion;
	}
	public void setTipoComunicacion(Integer tipoComunicacion) {
		this.tipoComunicacion = tipoComunicacion;
	}
	public String getValorComunicacion() {
		return valorComunicacion;
	}
	public void setValorComunicacion(String valorComunicacion) {
		this.valorComunicacion = valorComunicacion;
	}
	
	public String getValidada() {
		return validada;
	}
	public void setValidada(String validada) {
		this.validada = validada;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
}
