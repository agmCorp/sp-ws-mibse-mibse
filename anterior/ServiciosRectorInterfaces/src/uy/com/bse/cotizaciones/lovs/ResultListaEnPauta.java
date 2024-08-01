package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaEnPauta extends ResultGenerico{
	private ArrayList<EnPauta> enPauta;
	
	public ResultListaEnPauta() {		
		this.enPauta = new ArrayList<EnPauta>();
	}	

	public ArrayList<EnPauta> getEnPauta() {
		return enPauta;
	}

	public void setEnPauta(ArrayList<EnPauta> enPauta) {
		this.enPauta = enPauta;
	}

	public void setUnEnPauta(EnPauta pauta){
		this.enPauta.add(pauta);
	}
}
