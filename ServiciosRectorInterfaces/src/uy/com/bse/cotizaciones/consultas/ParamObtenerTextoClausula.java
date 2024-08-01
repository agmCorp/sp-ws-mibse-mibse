package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerTextoClausula extends ParamGenerico{
	private String codRamo;
	private String codClausula;
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodClausula() {
		return codClausula;
	}
	public void setCodClausula(String codClausula) {
		this.codClausula = codClausula;
	}
	
	

}
