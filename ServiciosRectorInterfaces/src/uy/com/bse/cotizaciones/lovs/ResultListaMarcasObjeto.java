package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaMarcasObjeto extends ResultGenerico{
	private ArrayList<MarcaObjeto> marcasObjeto = new ArrayList<MarcaObjeto>();

	public ArrayList<MarcaObjeto> getMarcasObjeto() {
		return marcasObjeto;
	}

	public void setMarcasObjeto(ArrayList<MarcaObjeto> marcasObjeto) {
		this.marcasObjeto = marcasObjeto;
	}
	
	public void setUnElemento(MarcaObjeto item){
		this.marcasObjeto.add(item);
	}

}
