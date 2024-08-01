package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDatosBasicosCotiza extends ParamGenerico{
	private String codRamo;
	private String codProducto;
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

}
