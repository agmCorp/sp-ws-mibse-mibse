package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTipoTexto extends ResultGenerico{
	private ArrayList<TipoTexto> tiposTexto = new ArrayList<TipoTexto>();

	public ArrayList<TipoTexto> getTiposTexto() {
		return tiposTexto;
	}

	public void setTiposTexto(ArrayList<TipoTexto> tiposTexto) {
		this.tiposTexto = tiposTexto;
	}

	public void setUnTipoTexto(TipoTexto item){
		this.tiposTexto.add(item);
	}
}
