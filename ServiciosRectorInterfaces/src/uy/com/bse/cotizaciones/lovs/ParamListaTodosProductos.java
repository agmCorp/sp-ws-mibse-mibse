package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamListaTodosProductos extends ParamGenerico{
	private static final long serialVersionUID = -6349762054952752269L;
	private Integer codRamo;

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
}
