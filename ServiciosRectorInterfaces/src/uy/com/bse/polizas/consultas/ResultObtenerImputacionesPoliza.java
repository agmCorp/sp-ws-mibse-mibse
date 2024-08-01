package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Imputacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerImputacionesPoliza extends ResultGenerico{
	private EncabezadoCuota encabezado;
	private ArrayList<Imputacion> imputaciones = new ArrayList<Imputacion>();
	
	public EncabezadoCuota getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoCuota encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<Imputacion> getImputaciones() {
		return imputaciones;
	}
	public void setImputaciones(ArrayList<Imputacion> imputaciones) {
		this.imputaciones = imputaciones;
	}
	
	public void setUnaImputacion(Imputacion item){
		this.imputaciones.add(item);
	}
}
