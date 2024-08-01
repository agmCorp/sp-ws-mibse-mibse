package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Anexo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAnexosCotizacion extends ResultGenerico{
	private ArrayList<Anexo> anexos = new ArrayList<Anexo>();

	public ArrayList<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(ArrayList<Anexo> anexos) {
		this.anexos = anexos;
	}
	
	public void setUnAnexo (Anexo item) {
		this.anexos.add(item);
	}
	

}
