package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamFacturacionElectronica extends ParamGenerico{

	private static final long serialVersionUID = -7073311510047317180L;
	private String numeroFactura;

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
}
