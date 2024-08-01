package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteIncentivoPlusTotal implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	
	private Integer codRamo;
	private String producto;
	private String tipo;
	private Integer cantidad;
	private Double totalPuntos;
	private String desde;
	private String plan;
	private String hasta;
	
	
	public ReporteIncentivoPlusTotal(Integer codRamo, String producto, String tipo, Integer cantidad, Double totalPuntos, String desde, String plan, String hasta) {
		super();
		this.codRamo = codRamo;
		this.producto = producto;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.totalPuntos = totalPuntos;
		this.desde = desde;
		this.plan = plan;
		this.hasta = hasta;
	}
	public ReporteIncentivoPlusTotal() {
		super();
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
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
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getTotalPuntos() {
		return totalPuntos;
	}
	public void setTotalPuntos(Double totalPuntos) {
		this.totalPuntos = totalPuntos;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
}
