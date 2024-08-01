package uy.com.bse.afiliaciones;

import java.io.Serializable;

public class BancoAfiliacionCuenta implements Serializable {
	private static final long serialVersionUID = -8808532874933977853L;
	
	private String codigo;
	private String nombre;
	private String pais;
	private String fechaHasta;
	private Boolean sucursal;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Boolean getSucursal() {
		return sucursal;
	}

	public void setSucursal(Boolean sucursal) {
		this.sucursal = sucursal;
	}
}
