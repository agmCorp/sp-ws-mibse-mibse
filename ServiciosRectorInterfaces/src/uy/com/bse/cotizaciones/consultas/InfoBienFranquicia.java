package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

public class InfoBienFranquicia extends BienCert {
	private ArrayList<Franquicia> franquicias  = new ArrayList<Franquicia>();

	public ArrayList<Franquicia> getFranquicias() {
		return franquicias;
	}

	public void setFranquicias(ArrayList<Franquicia> franquicias) {
		this.franquicias = franquicias;
	}
	
	public void setUnaFranquicia(Franquicia item){
		this.franquicias.add(item);
	}
}
