package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCabezalCotizaciones extends ResultGenerico{
	private ArrayList<Cotizacion> cotizacion;
	
	public ResultObtenerCabezalCotizaciones(){
		this.cotizacion = new ArrayList<Cotizacion>();
	}

	public ArrayList<Cotizacion> getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(ArrayList<Cotizacion> cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public void setUnElemento(Cotizacion item){
		this.cotizacion.add(item);
	}
}
