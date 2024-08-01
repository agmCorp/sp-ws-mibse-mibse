package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.ObjetoBien;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerListaBienesPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza;
	private ArrayList<ObjetoBien> listaBienes = new ArrayList<ObjetoBien>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<ObjetoBien> getListaBienes() {
		return listaBienes;
	}
	public void setListaBienes(ArrayList<ObjetoBien> listaBienes) {
		this.listaBienes = listaBienes;
	}
	
	public void setUnObjeto(ObjetoBien item){
		this.listaBienes.add(item);
	}
}
