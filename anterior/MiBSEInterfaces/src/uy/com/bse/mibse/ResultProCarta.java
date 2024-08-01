package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultProCarta extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	
	private String nomReporte;
	private int nuCarta;
	
	public String getNomReporte() {
		return nomReporte;
	}
	public void setNomReporte(String nomReporte) {
		this.nomReporte = nomReporte;
	}
	public int getNuCarta() {
		return nuCarta;
	}
	public void setNuCarta(int nuCarta) {
		this.nuCarta = nuCarta;
	}
}
