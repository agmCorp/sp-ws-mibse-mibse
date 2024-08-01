package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamProducto extends ParamGenerico{
	private Integer codRamo;

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
}
