package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerCabezalCotizaciones extends ParamGenerico{	
	private static final long serialVersionUID = 6182465078478136275L;
	private String ramo;
	private String producto;
	private String tipo;
	private String modificada;
	private String enPauta;
	private String numCotizacion;	
	private String fechaDesde;
	private String fechaHasta;
	private String nombreCliente;
	private String apellidoRazonSocialCliente;
	private String numPoliza;
	private Integer orden;
	private String codMoneda;
	private String codProductor;

	
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getModificada() {
		return modificada;
	}
	public void setModificada(String modificada) {
		this.modificada = modificada;
	}
	public String getEnPauta() {
		return enPauta;
	}
	public void setEnPauta(String enPauta) {
		this.enPauta = enPauta;
	}
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoRazonSocialCliente() {
		return apellidoRazonSocialCliente;
	}
	public void setApellidoRazonSocialCliente(String apellidoRazonSocialCliente) {
		this.apellidoRazonSocialCliente = apellidoRazonSocialCliente;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}


}
