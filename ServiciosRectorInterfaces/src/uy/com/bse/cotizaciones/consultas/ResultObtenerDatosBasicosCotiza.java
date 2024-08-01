package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.cotizaciones.entidades.DatosBasicosCotizacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosBasicosCotiza extends ResultGenerico {
	private DatosBasicosCotizacion datosBasicos;
	
	public DatosBasicosCotizacion getDatosBasicos() {
		return datosBasicos;
	}
	public void setDatosBasicos(DatosBasicosCotizacion datosBasicos) {
		this.datosBasicos = datosBasicos;
	}
		
}
