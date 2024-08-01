package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class DatosBienCert implements Serializable{
	private Integer datoCod;
	private String datoDesc;
	private String valorCod;
	private String valorDesc;
	private String datoTipo;
	private String visibilidad;
	private String requerido;
	private Integer decimales;
	private Integer tabla;
	private Integer longitud;
	private Integer datoPosicion;
	private String obligatorio;
	private String cotizar;
	private Integer cantidadFiltros;
	private String tieneReglas;
	
	public Integer getDatoPosicion() {
		return datoPosicion;
	}
	public void setDatoPosicion(Integer datoPosicion) {
		this.datoPosicion = datoPosicion;
	}
			
	public String getObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}
	public Integer getDatoCod() {
		return datoCod;
	}
	public void setDatoCod(Integer datoCod) {
		this.datoCod = datoCod;
	}
	public String getDatoDesc() {
		return datoDesc;
	}
	public void setDatoDesc(String datoDesc) {
		this.datoDesc = datoDesc;
	}
	public String getValorCod() {
		return valorCod;
	}
	public void setValorCod(String valorCod) {
		this.valorCod = valorCod;
	}
	public String getValorDesc() {
		return valorDesc;
	}
	public void setValorDesc(String valorDesc) {
		this.valorDesc = valorDesc;
	}
	public String getDatoTipo() {
		return datoTipo;
	}
	public void setDatoTipo(String datoTipo) {
		this.datoTipo = datoTipo;
	}
	public String getVisibilidad() {
		return visibilidad;
	}
	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	public Integer getDecimales() {
		return decimales;
	}
	public void setDecimales(Integer decimales) {
		this.decimales = decimales;
	}
	public Integer getTabla() {
		return tabla;
	}
	public void setTabla(Integer tabla) {
		this.tabla = tabla;
	}
	public Integer getLongitud() {
		return longitud;
	}
	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}
	public String getCotizar() {
		return cotizar;
	}
	public void setCotizar(String cotizar) {
		this.cotizar = cotizar;
	}
	public Integer getCantidadFiltros() {
		return cantidadFiltros;
	}
	public void setCantidadFiltros(Integer cantidadFiltros) {
		this.cantidadFiltros = cantidadFiltros;
	}
	public String getTieneReglas() {
		return tieneReglas;
	}
	public void setTieneReglas(String tieneReglas) {
		this.tieneReglas = tieneReglas;
	}
	
}
