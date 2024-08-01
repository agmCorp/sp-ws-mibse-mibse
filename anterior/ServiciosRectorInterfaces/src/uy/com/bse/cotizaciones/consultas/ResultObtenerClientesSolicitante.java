package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerClientesSolicitante extends ResultGenerico{
	private ArrayList<Cliente> clientes= new ArrayList<Cliente>();

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void setUnClienteSolicitante(Cliente item){
		this.clientes.add(item);
	}
	
}
