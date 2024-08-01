package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class TipoAnulacion implements Serializable{
	private static final long serialVersionUID = -6630756611358275643L;
	private String codigo;
	private String descripcion;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
