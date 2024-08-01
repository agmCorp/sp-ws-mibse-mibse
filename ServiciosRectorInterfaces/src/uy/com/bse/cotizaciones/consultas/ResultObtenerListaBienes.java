package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.ObjetoBien;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerListaBienes extends ResultGenerico{
	private ArrayList<ObjetoBien> listaObjetosDelBien= new ArrayList<ObjetoBien>();
	private BienCert bien;
	
	public ArrayList<ObjetoBien> getListaObjetosDelBien() {
		return listaObjetosDelBien;
	}
	public void setListaObjetosDelBien(ArrayList<ObjetoBien> listaObjetosDelBien) {
		this.listaObjetosDelBien = listaObjetosDelBien;
	}
	public BienCert getBien() {
		return bien;
	}
	public void setBien(BienCert bien) {
		this.bien = bien;
	}
	
	public void setUnElemento(ObjetoBien item) {
		this.listaObjetosDelBien.add(item);
	}
}
