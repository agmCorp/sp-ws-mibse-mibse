package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValoresTipoNota extends ResultGenerico{
	
	ArrayList<TiposNotaGenerica> elementos = new ArrayList<TiposNotaGenerica>();

	public ArrayList<TiposNotaGenerica> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<TiposNotaGenerica> elementos) {
		this.elementos = elementos;
	}
	
	public void setUnElemento(TiposNotaGenerica item){
		this.elementos.add(item);
	}
	
	

}
