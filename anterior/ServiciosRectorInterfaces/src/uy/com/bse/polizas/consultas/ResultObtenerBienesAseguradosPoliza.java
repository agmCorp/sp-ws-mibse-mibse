package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerBienesAseguradosPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza ;
	private ArrayList<BienAsegurado> bienesAsegurados = new ArrayList<BienAsegurado>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<BienAsegurado> getBienesAsegurados() {
		return bienesAsegurados;
	}
	public void setBienesAsegurados(ArrayList<BienAsegurado> bienesAsegurados) {
		this.bienesAsegurados = bienesAsegurados;
	}
	
	public void setUnBienAsegurado(BienAsegurado item){
		this.bienesAsegurados.add(item);
	}
	
}
