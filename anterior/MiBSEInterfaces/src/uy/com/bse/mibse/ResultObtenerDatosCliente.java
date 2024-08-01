package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosCliente extends ResultGenerico{

	private static final long serialVersionUID = 6033134603484763981L;
	
	private DatosCliente cliente;

	public DatosCliente getCliente() {
		return cliente;
	}

	public void setCliente(DatosCliente cliente) {
		this.cliente = cliente;
	}
	

}