package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCausaAnulacion extends ParamGenerico{
	private String codTipoAnulacion;
	private String codRamo;
	
	public String getCodTipoAnulacion() {
		return codTipoAnulacion;
	}
	public void setCodTipoAnulacion(String codTipoAnulacion) {
		this.codTipoAnulacion = codTipoAnulacion;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}

}
