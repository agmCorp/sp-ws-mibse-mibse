package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Cuota;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCuotasPoliza extends ResultGenerico{
	private EncabezadoCuota encabezado;
	private ArrayList<Cuota> cuotas = new ArrayList<Cuota>();
	
	public EncabezadoCuota getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoCuota encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<Cuota> getCuotas() {
		return cuotas;
	}
	public void setCuotas(ArrayList<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	public void setUnaCuota(Cuota item){
		this.cuotas.add(item);
	}

}
