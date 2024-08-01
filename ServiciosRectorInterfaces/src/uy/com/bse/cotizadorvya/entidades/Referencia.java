package uy.com.bse.cotizadorvya.entidades;

import java.io.Serializable;

public class Referencia implements Serializable {
	private static final long serialVersionUID = -893855425163185430L;
	
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
