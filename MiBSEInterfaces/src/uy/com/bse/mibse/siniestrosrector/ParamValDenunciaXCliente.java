/**
 * ResultObtenerMapaStro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValDenunciaXCliente extends ParamGenerico {
	
	private static final long serialVersionUID = 1L;
	
	private  String 	nroCliente;
	private  String 	serie;
	private  Integer  	numDenuncia;
	private  String 	porAsegurado; // S o N
	private  String 	porContratante; // S o N
	private  String 	fechaDesde; //DDMMYYYY
	
	public String getNroCliente() {
		return nroCliente;
	}
	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Integer getNumDenuncia() {
		return numDenuncia;
	}
	public void setNumDenuncia(Integer numDenuncia) {
		this.numDenuncia = numDenuncia;
	}
	public String getPorAsegurado() {
		return porAsegurado;
	}
	public void setPorAsegurado(String porAsegurado) {
		this.porAsegurado = porAsegurado;
	}
	public String getPorContratante() {
		return porContratante;
	}
	public void setPorContratante(String porContratante) {
		this.porContratante = porContratante;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
}