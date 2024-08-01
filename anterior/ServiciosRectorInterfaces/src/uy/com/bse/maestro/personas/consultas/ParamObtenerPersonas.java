package uy.com.bse.maestro.personas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPersonas extends ParamGenerico{
	private String primerCriterio;
	private String primerOperador;
	private String primerValor;
	private String segundoCriterio;
	private String segundoOperador;
	private String segundoValor;
	private String operadorLogico;
	
	public String getOperadorLogico() {
		return operadorLogico;
	}
	public void setOperadorLogico(String operadorLogico) {
		this.operadorLogico = operadorLogico;
	}
	public String getPrimerCriterio() {
		return primerCriterio;
	}
	public void setPrimerCriterio(String primerCriterio) {
		this.primerCriterio = primerCriterio;
	}
	public String getPrimerOperador() {
		return primerOperador;
	}
	public void setPrimerOperador(String primerOperador) {
		this.primerOperador = primerOperador;
	}
	public String getPrimerValor() {
		return primerValor;
	}
	public void setPrimerValor(String primerValor) {
		this.primerValor = primerValor;
	}
	public String getSegundoCriterio() {
		return segundoCriterio;
	}
	public void setSegundoCriterio(String segundoCriterio) {
		this.segundoCriterio = segundoCriterio;
	}
	public String getSegundoOperador() {
		return segundoOperador;
	}
	public void setSegundoOperador(String segundoOperador) {
		this.segundoOperador = segundoOperador;
	}
	public String getSegundoValor() {
		return segundoValor;
	}
	public void setSegundoValor(String segundoValor) {
		this.segundoValor = segundoValor;
	}
}
