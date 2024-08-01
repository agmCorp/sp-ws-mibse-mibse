package uy.com.bse.mibse;

public class ParamInformarPagoBancario extends ParamInformarPagoTienda {
	private static final long serialVersionUID = 2553509391618588282L;
	
	private String codigoAutorizacion; // Número de transacción del banco

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}
}
