package uy.com.bse.maestro.personas.comunicaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAltaComunicacion extends ParamGenerico{
	
	private Integer codPersona;
	private Integer tipoComunicacion;
	private String valorComunicacion;
	private String fechaActualizacion;
	private String nota;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
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
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
