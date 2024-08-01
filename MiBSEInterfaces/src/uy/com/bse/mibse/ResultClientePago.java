package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultClientePago extends ResultGenerico {
	private static final long serialVersionUID = 5121584037688503193L;

	private ClientePago clientePago;
	
	public ResultClientePago() {
		super();
		clientePago = null;
	}

	public ClientePago getClientePago() {
		return clientePago;
	}

	public void setClientePago(ClientePago clientePago) {
		this.clientePago = clientePago;
	}
}
