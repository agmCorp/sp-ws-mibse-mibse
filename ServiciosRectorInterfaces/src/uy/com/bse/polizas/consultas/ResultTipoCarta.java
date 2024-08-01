package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.TipoCarta;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTipoCarta extends ResultGenerico{
	private static final long serialVersionUID = -7829011977949871832L;
	private ArrayList<TipoCarta> tipos = new ArrayList<TipoCarta>();

	public void setUno(TipoCarta carta) {
		this.tipos.add(carta);
	}

	public ArrayList<TipoCarta> getTipos() {
		return tipos;
	}

	public void setTipos(ArrayList<TipoCarta> tipos) {
		this.tipos = tipos;
	}
	
}
