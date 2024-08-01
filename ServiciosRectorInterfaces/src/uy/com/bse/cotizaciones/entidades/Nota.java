package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.polizas.entidades.ParametroNotaPoliza;

public class Nota implements Serializable{
	private String enlace;
	private Integer numNota;
	private String tipoNota;
	private String descripNota;
	private String fechaActualizacion;
	private String observacion;
	private String usuario;
	private Integer codEstadoNota;
	private String descripEstadoNota;
	private String numComprobante;
	private String descripSector;
	private Integer codSubTipoNota;
	private String descSubTipoNota;
	
	
	public Integer getCodSubTipoNota() {
		return codSubTipoNota;
	}
	public void setCodSubTipoNota(Integer codSubTipoNota) {
		this.codSubTipoNota = codSubTipoNota;
	}
	public String getDescSubTipoNota() {
		return descSubTipoNota;
	}
	public void setDescSubTipoNota(String descSubTipoNota) {
		this.descSubTipoNota = descSubTipoNota;
	}
	private ArrayList<ParametroNotaPoliza> parametrosNotas = new ArrayList<ParametroNotaPoliza>();
	
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public Integer getNumNota() {
		return numNota;
	}
	public void setNumNota(Integer numNota) {
		this.numNota = numNota;
	}
	public String getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}
	public String getDescripNota() {
		return descripNota;
	}
	public void setDescripNota(String descripNota) {
		this.descripNota = descripNota;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getCodEstadoNota() {
		return codEstadoNota;
	}
	public void setCodEstadoNota(Integer codEstadoNota) {
		this.codEstadoNota = codEstadoNota;
	}
	public String getDescripEstadoNota() {
		return descripEstadoNota;
	}
	public String getNumComprobante() {
		return numComprobante;
	}
	public void setNumComprobante(String numComprobante) {
		this.numComprobante = numComprobante;
	}
	public void setDescripEstadoNota(String descripEstadoNota) {
		this.descripEstadoNota = descripEstadoNota;
	}
	public String getDescripSector() {
		return descripSector;
	}
	public void setDescripSector(String descripSector) {
		this.descripSector = descripSector;
	}
	public ArrayList<ParametroNotaPoliza> getParametrosNotas() {
		return parametrosNotas;
	}
	public void setParametrosNotas(ArrayList<ParametroNotaPoliza> parametrosNotas) {
		this.parametrosNotas = parametrosNotas;
	}		

}
