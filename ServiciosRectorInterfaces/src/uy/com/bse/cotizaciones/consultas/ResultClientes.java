package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultClientes extends ResultGenerico{
	private ArrayList<Cliente> cliente;
	
	public ResultClientes(){
		this.cliente = new ArrayList<Cliente>();
	}

	public ArrayList<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(ArrayList<Cliente> cliente) {
		this.cliente = cliente;
	}
	
	public void setUnElemento(Cliente item){
		this.cliente.add(item);
	}
}
