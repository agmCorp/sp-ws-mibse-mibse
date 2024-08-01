package uy.com.bse.reportes;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerRutaDescargaUsuario extends ResultGenerico {

	private static final long serialVersionUID = 8816751106677383700L;
	
	private String ruta;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


}
