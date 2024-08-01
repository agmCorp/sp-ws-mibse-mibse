package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPolizasCabezal extends ParamGenerico{

	private static final long serialVersionUID = -3300958153017031922L;
	private String codRamo;
	private String numPoliza;
	private String fechaDesde;
	private String fechaEmision;
	private String fechaEmisionHasta;
	private String codCliente;
	private String cliente;
	private String asegurado;
	private String codProducto;
	private String fechaHasta;
	private String vigentes;
	private Integer orden;
	private String valorDatoParametrico;
	private Integer codDatoParametrico;
	private String codProductor;
	private Boolean ultimoEndoso;
	
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getVigentes() {
		return vigentes;
	}
	public void setVigentes(String vigentes) {
		this.vigentes = vigentes;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getValorDatoParametrico() {
		return valorDatoParametrico;
	}
	public void setValorDatoParametrico(String valorDatoParametrico) {
		this.valorDatoParametrico = valorDatoParametrico;
	}
	public Integer getCodDatoParametrico() {
		return codDatoParametrico;
	}
	public void setCodDatoParametrico(Integer codDatoParametrico) {
		this.codDatoParametrico = codDatoParametrico;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	public String getFechaEmisionHasta() {
		return fechaEmisionHasta;
	}
	public void setFechaEmisionHasta(String fechaEmisionHasta) {
		this.fechaEmisionHasta = fechaEmisionHasta;
	}
	public Boolean getUltimoEndoso() {
		return ultimoEndoso;
	}
	public void setUltimoEndoso(Boolean ultimoEndoso) {
		this.ultimoEndoso = ultimoEndoso;
	}
				
}
