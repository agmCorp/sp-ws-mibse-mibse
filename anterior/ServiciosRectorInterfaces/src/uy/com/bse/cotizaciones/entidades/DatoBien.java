package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class DatoBien implements Serializable{
	
	private static final long serialVersionUID = 6610899884808185546L;
	private Integer codDato;
	private String valorDato;
	private String filtro;
	private String habilitado;
	
	
	
	public Integer getCodDato() {
		return codDato;
	}
	public void setCodDato(Integer codDato) {
		this.codDato = codDato;
	}
	public String getValorDato() {
		return valorDato;
	}
	public void setValorDato(String valorDato) {
		this.valorDato = valorDato;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public String getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

}
