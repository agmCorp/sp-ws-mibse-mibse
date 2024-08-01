package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerFranquicias extends ResultGenerico{
	private ArrayList<InfoBienFranquicia> listaInfoFranquicias= new ArrayList<InfoBienFranquicia>();

	public ArrayList<InfoBienFranquicia> getListaInfoFranquicias() {
		return listaInfoFranquicias;
	}

	public void setListaInfoFranquicias(
			ArrayList<InfoBienFranquicia> listaInfoFranquicias) {
		this.listaInfoFranquicias = listaInfoFranquicias;
	}
	
	public void setUnElemento(InfoBienFranquicia item){
		this.listaInfoFranquicias.add(item);
	}

}
