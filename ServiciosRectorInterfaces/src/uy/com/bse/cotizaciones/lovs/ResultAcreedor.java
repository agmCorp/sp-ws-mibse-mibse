package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Acreedor;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAcreedor extends ResultGenerico{
	private ArrayList<Acreedor> acreedores = new ArrayList<Acreedor>();

	public ArrayList<Acreedor> getAcreedores() {
		return acreedores;
	}

	public void setAcreedores(ArrayList<Acreedor> acreedores) {
		this.acreedores = acreedores;
	}

	public void setUnAcreedor(Acreedor item){
		this.acreedores.add(item);
	}
}
