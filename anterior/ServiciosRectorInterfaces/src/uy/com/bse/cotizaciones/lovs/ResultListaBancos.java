package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.utilitario.dato.ResultGenerico;


public class ResultListaBancos extends ResultGenerico{
	private ArrayList<DatosBanco> datosBancos = new ArrayList<DatosBanco>();

	public ArrayList<DatosBanco> getDatosBancos() {
		return datosBancos;
	}

	public void setDatosBancos(ArrayList<DatosBanco> datosBancos) {
		this.datosBancos = datosBancos;
	}
	
	public void setUnDatoBanco(DatosBanco item){
		this.datosBancos.add(item);
	}
}
