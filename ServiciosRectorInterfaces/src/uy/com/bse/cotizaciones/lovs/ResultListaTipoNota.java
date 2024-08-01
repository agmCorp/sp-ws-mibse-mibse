package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTipoNota extends ResultGenerico{
	private ArrayList<TipoNota> tipoNota = new ArrayList<TipoNota>();
		
	public ArrayList<TipoNota> getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(ArrayList<TipoNota> tipoNota) {
		this.tipoNota = tipoNota;
	}
	
	public void setUnElemento(TipoNota item){
		this.tipoNota.add(item);
	}
	
}
