package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Acreedor;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAcreedoresPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza ;
	private ArrayList<Acreedor> acreedores = new ArrayList<Acreedor>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<Acreedor> getAcreedores() {
		return acreedores;
	}
	public void setAcreedores(ArrayList<Acreedor> acreedores) {
		this.acreedores = acreedores;
	}
	
	public void setUnAcreedor(Acreedor item){
		this.acreedores.add(item);
	}
}
