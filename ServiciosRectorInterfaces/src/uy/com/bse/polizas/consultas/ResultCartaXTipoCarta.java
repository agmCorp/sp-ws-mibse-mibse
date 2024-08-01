package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.CartaXTipoCarta;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCartaXTipoCarta extends ResultGenerico{
	private static final long serialVersionUID = 5321786792643602409L;
	private ArrayList<CartaXTipoCarta> cartas = new ArrayList<CartaXTipoCarta>();

	public ArrayList<CartaXTipoCarta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<CartaXTipoCarta> cartas) {
		this.cartas = cartas;
	}
	
	public void setUno(CartaXTipoCarta carta) {
		this.cartas.add(carta);
	}
	
}
