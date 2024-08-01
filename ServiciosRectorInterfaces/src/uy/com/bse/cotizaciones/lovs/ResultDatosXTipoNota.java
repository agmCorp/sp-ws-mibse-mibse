package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosXTipoNota extends ResultGenerico{
	
	ArrayList<String> etiquetas = new ArrayList<String>();

	public ArrayList<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(ArrayList<String> etiquetas) {
		this.etiquetas = etiquetas;
	}		
	
	public void setUnElemento(String item){
		this.etiquetas.add(item);
	}

}
