package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.polizas.entidades.Siniestro;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerSiniestrosCliente extends ResultGenerico{
	private Cliente datosCliente;
	private ArrayList<Siniestro> siniestros = new ArrayList<Siniestro>();
	
	public Cliente getDatosCliente() {
		return datosCliente;
	}
	public void setDatosCliente(Cliente datosCliente) {
		this.datosCliente = datosCliente;
	}
	public ArrayList<Siniestro> getSiniestros() {
		return siniestros;
	}
	public void setSiniestros(ArrayList<Siniestro> siniestros) {
		this.siniestros = siniestros;
	}
	public void setUnSiniestro(Siniestro item){
		this.siniestros.add(item);
	}
}
