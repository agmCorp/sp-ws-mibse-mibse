package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaPromocion extends ResultGenerico{
	private ArrayList<Promocion> promocion;
	
	public ResultListaPromocion() {		
		this.promocion = new ArrayList<Promocion>();
	}

	public ArrayList<Promocion> getPromocion() {
		return promocion;
	}

	public void setPromocion(ArrayList<Promocion> promocion) {
		this.promocion = promocion;
	}

	public void setUnElemento(Promocion item){
		this.promocion.add(item);
	}
}
