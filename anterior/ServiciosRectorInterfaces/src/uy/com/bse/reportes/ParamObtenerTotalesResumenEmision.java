package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerTotalesResumenEmision extends ParamGenerico {

	private static final long serialVersionUID = -4247778112427071528L;
	private String mesAnio;
	private String codProducto;
	private Integer codRamo;
	private String codProductor;
	
	public String getMesAnio() {
		return mesAnio;
	}
	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	
}
