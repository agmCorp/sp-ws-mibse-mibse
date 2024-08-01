package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamListaValoresDatoVehiculo extends ParamGenerico {
	
	private static final long serialVersionUID = -3979423351770798976L;
	private String codDato;
	private String cantFiltros;
	private String primerFiltro;
	private String segundoFiltro;
	
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	
	public String getCantFiltros() {
		return cantFiltros;
	}
	public void setCantFiltros(String cantFiltros) {
		this.cantFiltros = cantFiltros;
	}
	public String getPrimerFiltro() {
		return primerFiltro;
	}
	public void setPrimerFiltro(String primerFiltro) {
		this.primerFiltro = primerFiltro;
	}
	public String getSegundoFiltro() {
		return segundoFiltro;
	}
	public void setSegundoFiltro(String segundoFiltro) {
		this.segundoFiltro = segundoFiltro;
	}
	
}
