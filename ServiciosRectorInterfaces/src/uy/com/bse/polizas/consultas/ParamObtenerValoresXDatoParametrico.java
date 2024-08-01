package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerValoresXDatoParametrico extends ParamGenerico {

	private static final long serialVersionUID = -7537101972450125225L;
	private Integer codTabla;
	private String filtro;

	public Integer getCodTabla() {
		return codTabla;
	}

	public void setCodTabla(Integer codTabla) {
		this.codTabla = codTabla;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
