package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValoresDatosParametricosEndoso extends ResultGenerico{
	
	private ArrayList<String> valor = new ArrayList<String>();

	public ArrayList<String> getValor() {
		return valor;
	}

	public void setValor(ArrayList<String> valor) {
		this.valor = valor;
	}
	
	

}
