package uy.com.bse.autodata.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamConsultaAutodata extends ParamGenerico {

	private static final long serialVersionUID = 4298407794338290089L;

	private String codMarca;
	private String codAno;
	private String codModelo;

	public String getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getCodAno() {
		return codAno;
	}

	public void setCodAno(String codAno) {
		this.codAno = codAno;
	}

	public String getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}
}
