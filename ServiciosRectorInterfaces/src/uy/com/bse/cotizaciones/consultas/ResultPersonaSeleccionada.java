package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPersonaSeleccionada extends ResultGenerico{
	Cliente cliente = new Cliente();
	ArrayList<DireccionEC> direcciones = new ArrayList<DireccionEC>();
	
			
	public ArrayList<DireccionEC> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(ArrayList<DireccionEC> direcciones) {
		this.direcciones = direcciones;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
		
		
}
