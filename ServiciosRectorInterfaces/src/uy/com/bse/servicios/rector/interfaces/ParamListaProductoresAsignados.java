package uy.com.bse.servicios.rector.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamListaProductoresAsignados extends ParamGenerico{

	private static final long serialVersionUID = 3516418394656802295L;
	private Integer codRamo;
	private String codProducto;

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

}
