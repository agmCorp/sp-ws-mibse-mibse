package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamListaValoresDato extends ParamGenerico {
	
	private String numCotizacion;
	private String numCertificado;
	private String codDato;
	private String numConsBien;
	private String cantFiltros;
	private String codTabla;
	
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	public String getNumConsBien() {
		return numConsBien;
	}
	public void setNumConsBien(String numConsBien) {
		this.numConsBien = numConsBien;
	}
	public String getCantFiltros() {
		return cantFiltros;
	}
	public void setCantFiltros(String cantFiltros) {
		this.cantFiltros = cantFiltros;
	}
	public String getCodTabla() {
		return codTabla;
	}
	public void setCodTabla(String codTabla) {
		this.codTabla = codTabla;
	}
	

}
