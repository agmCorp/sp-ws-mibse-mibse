package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAcreedorObjetos extends ResultGenerico{
	private ArrayList<AcreedorObjetos> objetos = new ArrayList<AcreedorObjetos>();

	public ArrayList<AcreedorObjetos> getObjetos() {
		return objetos;
	}

	public void setObjetos(ArrayList<AcreedorObjetos> objetos) {
		this.objetos = objetos;
	}
	
	public void setUnObjeto(AcreedorObjetos item){
		this.objetos.add(item);
	}

}
