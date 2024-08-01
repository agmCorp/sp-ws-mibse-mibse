package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTiposNotaGenerica extends ResultGenerico{
	private ArrayList<TiposNotaGenerica> listaTiposNotaGenerica  = new ArrayList<TiposNotaGenerica>();

	public ArrayList<TiposNotaGenerica> getListaTiposNotaGenerica() {
		return listaTiposNotaGenerica;
	}

	public void setListaTiposNotaGenerica(
			ArrayList<TiposNotaGenerica> listaTiposNotaGenerica) {
		this.listaTiposNotaGenerica = listaTiposNotaGenerica;
	}
	
	public void setUnTipoNotaGenerica(TiposNotaGenerica item){
		this.listaTiposNotaGenerica.add(item);
	}
}
