package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosBasicosCliente;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerContratanteAsegurado extends ResultGenerico{
	ArrayList<DatosBasicosCliente> clientes = new ArrayList<DatosBasicosCliente>();

	public ArrayList<DatosBasicosCliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<DatosBasicosCliente> clientes) {
		this.clientes = clientes;
	}
	
	

}
