package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTipoAcreedores extends ResultGenerico{
	private ArrayList<TipoAcreedor> tipoAcreedores = new ArrayList<TipoAcreedor>();

	public ArrayList<TipoAcreedor> getTipoAcreedores() {
		return tipoAcreedores;
	}

	public void setTipoAcreedores(ArrayList<TipoAcreedor> tipoAcreedores) {
		this.tipoAcreedores = tipoAcreedores;
	}
	
	public void setUnTipo(TipoAcreedor item){
		this.tipoAcreedores.add(item);
	}
}
