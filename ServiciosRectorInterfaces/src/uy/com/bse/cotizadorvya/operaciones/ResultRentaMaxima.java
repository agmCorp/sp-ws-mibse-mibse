package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRentaMaxima extends ResultGenerico {
	private static final long serialVersionUID = -6050266605955155150L;
	
	private Double rentaMaxima;

	public Double getRentaMaxima() {
		return rentaMaxima;
	}

	public void setRentaMaxima(Double rentaMaxima) {
		this.rentaMaxima = rentaMaxima;
	}
}
