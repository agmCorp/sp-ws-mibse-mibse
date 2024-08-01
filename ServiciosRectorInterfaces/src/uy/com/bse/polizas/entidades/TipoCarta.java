package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class TipoCarta implements Serializable {
	private static final long serialVersionUID = -2038455423451699442L;
	private Integer codigo;
	private String descripcion;
	private String reporte;

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

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

}
