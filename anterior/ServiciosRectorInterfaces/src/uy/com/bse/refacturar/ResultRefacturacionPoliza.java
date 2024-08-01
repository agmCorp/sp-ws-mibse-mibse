package uy.com.bse.refacturar;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRefacturacionPoliza extends ResultGenerico{
	/**
	 * 
	 */
	private static final long serialVersionUID = -943751009168396490L;
	private Refacturacion detalleRefacturacion;

	public Refacturacion getDetalleRefacturacion() {
		return detalleRefacturacion;
	}

	public void setDetalleRefacturacion(Refacturacion detalleRefacturacion) {
		this.detalleRefacturacion = detalleRefacturacion;
	}

	
	

}
