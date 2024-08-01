package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTiposFacturacion extends ResultGenerico{
	private ArrayList<Facturacion> facturacion;
	
	public ResultListaTiposFacturacion() {		
		this.facturacion = new ArrayList<Facturacion>();
	}

	public ArrayList<Facturacion> getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(ArrayList<Facturacion> facturacion) {
		this.facturacion = facturacion;
	}

	public void setUnElemento(Facturacion item){
		this.facturacion.add(item);
	}
}
