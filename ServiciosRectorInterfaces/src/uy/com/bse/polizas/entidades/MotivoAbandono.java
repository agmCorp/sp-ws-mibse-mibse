package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class MotivoAbandono implements Serializable{
	private Integer codigo;
	private String descripcion;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
