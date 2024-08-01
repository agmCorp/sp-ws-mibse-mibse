package uy.com.bse.maestro.personas.comunicaciones;

import java.io.Serializable;

public class ComunicacionEC implements Serializable{
	private Integer codComunicacion;
	private Integer tipoComunicacion;
	private String valorComunicacion;
	private String fechaActualizacion;
	private String nota;
	private String validado;
	

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
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
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}	
	
	public String getValidado() {
		return validado;
	}
	public void setValidado(String validado) {
		this.validado = validado;
	}
}
