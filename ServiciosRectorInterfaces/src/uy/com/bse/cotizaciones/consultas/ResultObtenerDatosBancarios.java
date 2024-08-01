package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosBancarios extends ResultGenerico{
	
	private String requiereDatos;
	private String mensaje;
	private ArrayList<DatosBanco> datosBanco = new ArrayList<DatosBanco>();
	
	public String getRequiereDatos() {
		return requiereDatos;
	}
	public void setRequiereDatos(String requiereDatos) {
		this.requiereDatos = requiereDatos;
	}
	public ArrayList<DatosBanco> getDatosBanco() {
		return datosBanco;
	}
	public void setDatosBanco(ArrayList<DatosBanco> datosBanco) {
		this.datosBanco = datosBanco;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	


}
