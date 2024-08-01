package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamMontosIniciales extends ParamGenerico {
	private static final long serialVersionUID = 513352383260206823L;
	
	private Integer codMoneda;

	public Integer getCodMoneda() {
		return codMoneda;
	}
	
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
}
