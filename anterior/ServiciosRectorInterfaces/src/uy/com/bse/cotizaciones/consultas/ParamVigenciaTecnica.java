package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamVigenciaTecnica extends ParamGenerico{
	String codRamo;
	String codProducto;
	String tipoVigencia;
	
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
	public String getTipoVigencia() {
		return tipoVigencia;
	}
	public void setTipoVigencia(String tipoVigencia) {
		this.tipoVigencia = tipoVigencia;
	}
	

}
