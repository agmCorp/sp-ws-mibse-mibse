package uy.com.bse.cotizadores.pymes;


import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultNuevaCotizacionPymes extends ResultGenerico{
	
	
	private static final long serialVersionUID = 5526379621999111510L;
	private DatosCotizacionPymes datosCotizacion;
	
	
	public DatosCotizacionPymes getDatosCotizacion() {
		return datosCotizacion;
	}
	public void setDatosCotizacion(DatosCotizacionPymes datosCotizacion) {
		this.datosCotizacion = datosCotizacion;
	}

}
