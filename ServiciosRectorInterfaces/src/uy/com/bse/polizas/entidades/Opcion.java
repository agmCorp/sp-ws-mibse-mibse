package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Opcion implements Serializable {
	private static final long serialVersionUID = -2038455423451699442L;
	private String codigo;
	private String descripcion;
	private String seleccionada;

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

	public String getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(String seleccionada) {
		this.seleccionada = seleccionada;
	}

}
