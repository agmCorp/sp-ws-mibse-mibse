package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteResumenEmision implements Serializable {

	private static final long serialVersionUID = -2093475606746857940L;
	private Integer numPoliza;
	private Integer codRamo;
	private String descRamo;
	private String codProducto;
	private String descProducto;
	private String cliente;
	private Integer sucursal;
	private String usuarioEmisor;
	private String fechaDesde;
	private String fechaHasta;
	private String tipoRenovacion;
	private String motivoAnulacion;
	private Integer codMotivoAnulacion;
	private Integer numPolizaNueva;
	private String usuarioEmisorNueva;
	private Integer numCliente;

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public String getDescRamo() {
		return descRamo;
	}

	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public String getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public void setUsuarioEmisor(String usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
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

	public String getTipoRenovacion() {
		return tipoRenovacion;
	}

	public void setTipoRenovacion(String tipoRenovacion) {
		this.tipoRenovacion = tipoRenovacion;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public Integer getNumPolizaNueva() {
		return numPolizaNueva;
	}

	public void setNumPolizaNueva(Integer numPolizaNueva) {
		this.numPolizaNueva = numPolizaNueva;
	}

	public String getUsuarioEmisorNueva() {
		return usuarioEmisorNueva;
	}

	public void setUsuarioEmisorNueva(String usuarioEmisorNueva) {
		this.usuarioEmisorNueva = usuarioEmisorNueva;
	}

	public Integer getCodMotivoAnulacion() {
		return codMotivoAnulacion;
	}

	public void setCodMotivoAnulacion(Integer codMotivoAnulacion) {
		this.codMotivoAnulacion = codMotivoAnulacion;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}
}
