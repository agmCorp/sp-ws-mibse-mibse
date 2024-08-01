package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaOrigenPago extends ResultGenerico{
	private ArrayList<OrigenPago> origenPago;
	
	public ResultListaOrigenPago() {		
		this.origenPago = new ArrayList<OrigenPago>();
	}	

	public ArrayList<OrigenPago> getOrigenPago() {
		return origenPago;
	}

	public void setOrigenPago(ArrayList<OrigenPago> origenPago) {
		this.origenPago = origenPago;
	}

	public void setUnElemento(OrigenPago item){
		this.origenPago.add(item);
	}
}
