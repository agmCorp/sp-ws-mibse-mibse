package uy.com.bse.maestro.personas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPersonasClientes extends ResultGenerico{
	private ArrayList<Cliente> personasClientes = new ArrayList<Cliente>();

	public ArrayList<Cliente> getPersonasClientes() {
		return personasClientes;
	}

	public void setPersonasClientes(ArrayList<Cliente> personasClientes) {
		this.personasClientes = personasClientes;
	}
	
	public void setUnCliente(Cliente item){
		this.personasClientes.add(item);
	}
}
