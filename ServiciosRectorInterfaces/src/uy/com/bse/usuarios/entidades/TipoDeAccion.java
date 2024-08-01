package uy.com.bse.usuarios.entidades;

import java.io.Serializable;

public class TipoDeAccion implements Serializable{
	private String nombre;
	private Integer total;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
