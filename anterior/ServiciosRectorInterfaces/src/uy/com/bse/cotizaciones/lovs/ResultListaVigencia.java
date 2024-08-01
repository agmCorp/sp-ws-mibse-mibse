package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaVigencia extends ResultGenerico{
	private ArrayList<Vigencia> vigencia;
	
	public ResultListaVigencia() {		
		this.vigencia = new ArrayList<Vigencia>();
	}
	
	public ArrayList<Vigencia> getVigencia() {
		return vigencia;
	}

	public void setVigencia(ArrayList<Vigencia> vigencia) {
		this.vigencia = vigencia;
	}


	public void setUnElemento(Vigencia item){
		this.vigencia.add(item);
	}
}
