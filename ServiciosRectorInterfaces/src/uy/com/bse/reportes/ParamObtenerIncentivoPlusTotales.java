package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerIncentivoPlusTotales extends ParamGenerico {

	private static final long serialVersionUID = -4247778112427071528L;
	private String mesDesde;
	private String mesHasta;
	private Integer codRamo;
	private String tipo;

	public String getMesDesde() {
		 return mesDesde;
	}

	public String getMesHasta() {
		return mesHasta;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setMesDesde(String mesDesde) {
		this.mesDesde = mesDesde;
	}

	public void setMesHasta(String mesHasta) {
		this.mesHasta = mesHasta;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
