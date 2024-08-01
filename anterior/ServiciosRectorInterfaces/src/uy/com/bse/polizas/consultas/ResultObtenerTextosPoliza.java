package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Texto;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerTextosPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza;
	private ArrayList<Texto> textos =  new ArrayList<Texto>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<Texto> getTextos() {
		return textos;
	}
	public void setTextos(ArrayList<Texto> textos) {
		this.textos = textos;
	}
	
	public void setUnTexto(Texto item){
		this.textos.add(item);
	}
}
