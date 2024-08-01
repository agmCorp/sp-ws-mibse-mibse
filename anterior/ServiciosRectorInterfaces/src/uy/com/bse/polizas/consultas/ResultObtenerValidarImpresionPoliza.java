package uy.com.bse.polizas.consultas;

import uy.com.bse.polizas.entidades.ImpresionPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerValidarImpresionPoliza extends ResultGenerico{

	private static final long serialVersionUID = -5962865940019037761L;
	
	private ImpresionPoliza impresionPoliza;

	public ImpresionPoliza getImpresionPoliza() {
		return impresionPoliza;
	}

	public void setImpresionPoliza(ImpresionPoliza impresionPoliza) {
		this.impresionPoliza = impresionPoliza;
	}
	
}
