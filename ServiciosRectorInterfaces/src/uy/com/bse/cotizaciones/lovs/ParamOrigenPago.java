package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamOrigenPago extends ParamGenerico{
	private Integer codMedioPago;

	public Integer getCodMedioPago() {
		return codMedioPago;
	}

	public void setCodMedioPago(Integer codMedioPago) {
		this.codMedioPago = codMedioPago;
	}
}
