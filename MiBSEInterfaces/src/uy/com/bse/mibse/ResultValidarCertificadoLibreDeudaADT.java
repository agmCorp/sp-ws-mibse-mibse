package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarCertificadoLibreDeudaADT extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	
	private String estado;
	private String titular;
	private String fecha_vigencia;
	private String producto;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getFecha_vigencia() {
		return fecha_vigencia;
	}
	public void setFecha_vigencia(String fecha_vigencia) {
		this.fecha_vigencia = fecha_vigencia;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}


}
