package uy.com.bse.utilitario.dato;

import java.io.Serializable;

public class ServiciosError implements Serializable{
	
	private static final long serialVersionUID = -4935719888947152824L;
	private Integer codigo;
	private String descripcion;
	
	public ServiciosError() {
		super();
		this.codigo=1;
	}
	
	public ServiciosError(String descripcion) {
		super();
		this.codigo=1;
		this.descripcion = descripcion;
	}
	public ServiciosError(Integer codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
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
