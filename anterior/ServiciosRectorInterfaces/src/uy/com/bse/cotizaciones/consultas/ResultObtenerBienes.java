package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.BienAgregar;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerBienes extends ResultGenerico{
	private ArrayList<BienAgregar> bienesAgregar = new ArrayList<BienAgregar>();

	public ArrayList<BienAgregar> getBienesAgregar() {
		return bienesAgregar;
	}

	public void setBienesAgregar(ArrayList<BienAgregar> bienesAgregar) {
		this.bienesAgregar = bienesAgregar;
	}
	
	public void setUnItem(BienAgregar item){
		this.bienesAgregar.add(item);
	}
}
