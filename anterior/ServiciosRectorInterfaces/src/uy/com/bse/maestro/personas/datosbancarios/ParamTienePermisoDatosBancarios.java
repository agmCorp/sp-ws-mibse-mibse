package uy.com.bse.maestro.personas.datosbancarios;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamTienePermisoDatosBancarios extends ParamGenerico {
	private static final long serialVersionUID = 3232813727729967800L;
	private Integer codPermiso;
	public Integer getCodPermiso() {
		return codPermiso;
	}
	public void setCodPermiso(Integer codPermiso) {
		this.codPermiso = codPermiso;
	}


}
