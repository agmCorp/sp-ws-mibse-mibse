package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerIncentivoPlusDetalle extends ParamGenerico{

	private static final long serialVersionUID = -4908550774718708212L;
	private String tipo;
	private Integer codRamo;
	private String mesAnio;

	public String getMesAnio() {
		return mesAnio;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}

}
