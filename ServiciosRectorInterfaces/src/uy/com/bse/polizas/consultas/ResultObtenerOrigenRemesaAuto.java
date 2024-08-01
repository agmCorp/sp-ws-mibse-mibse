package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerOrigenRemesaAuto extends ResultGenerico{
	private ArrayList<RemesaAutomatica> listaOrigenRemesa = new ArrayList<RemesaAutomatica>();

	public ArrayList<RemesaAutomatica> getListaOrigenRemesa() {
		return listaOrigenRemesa;
	}

	public void setListaOrigenRemesa(ArrayList<RemesaAutomatica> listaOrigenRemesa) {
		this.listaOrigenRemesa = listaOrigenRemesa;
	}
	
	public void setUnaRemesa(RemesaAutomatica item){
		this.listaOrigenRemesa.add(item);
	}

}
