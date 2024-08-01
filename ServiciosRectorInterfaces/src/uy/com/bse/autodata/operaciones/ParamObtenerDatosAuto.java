package uy.com.bse.autodata.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDatosAuto extends ParamGenerico {
	private static final long serialVersionUID = -8497844249810680216L;
	
	private String codAutoData;

	public String getCodAutoData() {
		return codAutoData;
	}

	public void setCodAutoData(String codAutoData) {
		this.codAutoData = codAutoData;
	}

	
}
