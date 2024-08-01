/**
 * ResultObtenerMapaStro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerMapaMsgSiniestro extends ParamGenerico {

	private static final long serialVersionUID = 1L;
	
	private  Integer ramo;
	private  String ruta;
	private  String tieneDenuncia;
	private  String tieneReclamo;
	private  String estadoReclamo;
	private  String tieneSiniestro;
	private  String tieneNota;
	private  int nroNota;
	private  String tieneSubNota;
	private  int nroSubNota;
	
	
	public Integer getRamo() {
		return ramo;
	}

	public void setRamo(Integer ramo) {
		this.ramo = ramo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getTieneDenuncia() {
		return tieneDenuncia;
	}

	public void setTieneDenuncia(String tieneDenuncia) {
		this.tieneDenuncia = tieneDenuncia;
	}

	public String getTieneReclamo() {
		return tieneReclamo;
	}

	public void setTieneReclamo(String tieneReclamo) {
		this.tieneReclamo = tieneReclamo;
	}

	public String getEstadoReclamo() {
		return estadoReclamo;
	}

	public void setEstadoReclamo(String estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}

	public String getTieneSiniestro() {
		return tieneSiniestro;
	}

	public void setTieneSiniestro(String tieneSiniestro) {
		this.tieneSiniestro = tieneSiniestro;
	}

	public String getTieneNota() {
		return tieneNota;
	}

	public void setTieneNota(String tieneNota) {
		this.tieneNota = tieneNota;
	}

	public int getNroNota() {
		return nroNota;
	}

	public void setNroNota(int nroNota) {
		this.nroNota = nroNota;
	}

	public String getTieneSubNota() {
		return tieneSubNota;
	}

	public void setTieneSubNota(String tieneSubNota) {
		this.tieneSubNota = tieneSubNota;
	}

	public int getNroSubNota() {
		return nroSubNota;
	}

	public void setNroSubNota(int nroSubNota) {
		this.nroSubNota = nroSubNota;
	}

	public String getNotasAnteriores() {
		return notasAnteriores;
	}

	public void setNotasAnteriores(String notasAnteriores) {
		this.notasAnteriores = notasAnteriores;
	}
	
	private  String notasAnteriores;
	}
