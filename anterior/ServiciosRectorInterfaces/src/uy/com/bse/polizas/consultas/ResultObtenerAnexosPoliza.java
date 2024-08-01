package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Anexo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAnexosPoliza extends ResultGenerico{
	EncabezadoDetallePoliza encabezadoDetallePoliza;
	private ArrayList<Anexo> anexos = new ArrayList<Anexo>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<Anexo> getAnexos() {
		return anexos;
	}
	public void setAnexos(ArrayList<Anexo> anexos) {
		this.anexos = anexos;
	}
	
	public void setUnAnexo(Anexo item){
		this.anexos.add(item);
	}
}
