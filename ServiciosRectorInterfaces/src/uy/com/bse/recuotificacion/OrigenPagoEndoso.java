package uy.com.bse.recuotificacion;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosBanco;

public class OrigenPagoEndoso implements Serializable{
	private String codigo;
	private String descripcion;
	
	private ArrayList<DatosBanco> datosBancarios = new ArrayList<DatosBanco>(2);
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setUno(DatosBanco datosBanco){
		this.datosBancarios.add(datosBanco);
	}
	public ArrayList<DatosBanco> getDatosBancarios() {
		return datosBancarios;
	}
	public void setDatosBancarios(ArrayList<DatosBanco> datosBancarios) {
		this.datosBancarios = datosBancarios;
	}
}
