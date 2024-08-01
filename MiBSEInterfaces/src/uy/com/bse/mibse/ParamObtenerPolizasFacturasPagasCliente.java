package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPolizasFacturasPagasCliente extends ParamGenerico {

	private static final long serialVersionUID = 8418166348881389837L;
	
	private Integer diasVencimientoPoliza;

	public Integer getDiasVencimientoPoliza() {
		return diasVencimientoPoliza;
	}

	public void setDiasVencimientoPoliza(Integer diasVencimientoPoliza) {
		this.diasVencimientoPoliza = diasVencimientoPoliza;
	}
	
}
