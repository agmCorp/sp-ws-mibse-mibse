package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosPoliza;
import uy.com.bse.polizas.entidades.Endoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetallePoliza extends ResultGenerico{
	private DatosPoliza detallePoliza;
	private ArrayList<Endoso> endoso = new ArrayList<Endoso>();
	
	public DatosPoliza getDetallePoliza() {
		return detallePoliza;
	}
	public void setDetallePoliza(DatosPoliza detallePoliza) {
		this.detallePoliza = detallePoliza;
	}
	public ArrayList<Endoso> getEndoso() {
		return endoso;
	}
	public void setEndoso(ArrayList<Endoso> endoso) {
		this.endoso = endoso;
	}
	
	public void setUnEndoso(Endoso item){
		this.endoso.add(item);
	}
	
}
