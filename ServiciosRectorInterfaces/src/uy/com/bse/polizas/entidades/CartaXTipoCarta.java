package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class CartaXTipoCarta implements Serializable{
	private static final long serialVersionUID = 336086699364601156L;
	private String codigo;
	private String descripcion;
	private String reporte;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	
}
